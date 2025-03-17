import { Injectable } from '@angular/core';
import { ChatbotMessage } from '../model/chatbot-message';
import { Observable, Subject } from 'rxjs';
import SockJS from 'sockjs-client';
import { Client, IMessage } from '@stomp/stompjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {
  private stompClient: Client | undefined;
  private messageSubject = new Subject<ChatbotMessage>();
  private pathService = 'http://localhost:8080/api/user';
  private baseUrl = 'http://localhost:8080/api/messages'; // Adjust the URL as needed


  constructor(private http: HttpClient) {
    this.initializeWebSocketConnection();
  }

  private initializeWebSocketConnection() {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      debug: (str) => console.log(str),
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    this.stompClient.onConnect = (frame) => {
      console.log('Connected: ' + frame);

      // S'abonner au topic public pour recevoir des messages
      this.stompClient?.subscribe('/topic/public', (message: IMessage) => {
        const chatMessage: ChatbotMessage = JSON.parse(message.body);
        this.messageSubject.next(chatMessage);
      });
    };

    this.stompClient.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    this.stompClient.activate();
  }

  sendMessage(message: ChatbotMessage) {
    this.stompClient?.publish({
      destination: '/app/sendMessage',
      body: JSON.stringify(message)
    });
  }

  getMessages(): Observable<ChatbotMessage[]> {
    return this.http.get<ChatbotMessage[]>(this.baseUrl);
  }

  sendName(user: { name: string }){
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.pathService, user, { headers });
  }

  getUserConnected(){
    return this.http.get(this.pathService);
  }
}
