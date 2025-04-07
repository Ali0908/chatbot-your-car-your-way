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
  messageSubject = new Subject<any>();
  private pathService = 'http://localhost:8080/api/user';
  private baseUrl = 'http://localhost:8080/api/messages';
  private usersUrl = 'http://localhost:8080/api/users';
  // private nextUser = 'http://localhost:8080/api/user/next';
  // private previousUser = 'http://localhost:8080/api/user/previous';


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
      // console.log('Connected: ' + frame);

      // S'abonner au topic public pour recevoir des messages
      this.stompClient?.subscribe('/topic/public', (message: IMessage) => {
        // console.log('Received message body:', message.body); // Log the raw message body
        const chatMessage: ChatbotMessage = JSON.parse(message.body);
        // console.log('Parsed chatMessage:', chatMessage); // Log the parsed chatMessage
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

  getAllUsers(): Observable<any> {
    return this.http.get(this.usersUrl);
  }

  // getUserConnected(){
  //   return this.http.get(this.pathService);
  // }
  //
  // getNextUser(): Observable<any> {
  //   return this.http.get<any>(this.nextUser);
  // }
  //
  // getPreviousUser(): Observable<any> {
  //       return this.http.get<any>(this.previousUser);
  // }

}
