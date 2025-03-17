import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ChatbotMessage} from '../model/chatbot-message';
import {ChatbotService} from '../services/chatbot.service';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatFormField} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule, DatePipe, MatButton, MatFormField, MatInput, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  messages: ChatbotMessage[] = [];
  newMessage: string = '';
  username: string = ''; // This value could be dynamic

  constructor(private chatbotService: ChatbotService, private cd: ChangeDetectorRef) {
  }

  ngOnInit(): void {
    // Subscribe to incoming messages
    this.chatbotService.getUserConnected().subscribe({
      next: (user: any) => {
        this.username = user?.name;
      }
    })
    this.getMessages();

  }

  send(): void {
    if (this.newMessage.trim()) {
      const message: ChatbotMessage = {
        sender: this.username,
        content: this.newMessage,
        timestamp: new Date()
      };
      this.chatbotService.sendMessage(message);
      this.newMessage = '';
      this.getMessages();
    }
  }

  getMessages() {
    this.chatbotService.getMessages().subscribe({
      next: (msg: ChatbotMessage[]) => {
        this.messages = msg;
      },
      error: () => {}
    });
  }
}
