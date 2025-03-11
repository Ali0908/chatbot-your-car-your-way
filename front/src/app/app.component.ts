import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe, CommonModule } from '@angular/common';
import {Button, ButtonDirective, ButtonLabel} from 'primeng/button';
import { ChatbotMessage } from './model/chatbot-message';
import { ChatbotService } from './services/chatbot.service';
import { Panel } from 'primeng/panel';
import { InputText } from 'primeng/inputtext';
import {FormHomeComponent} from './form-home/form-home.component';

@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule, DatePipe, ButtonDirective, Panel, ButtonLabel, InputText, Button, FormHomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  messages: ChatbotMessage[] = [];
  newMessage: string = '';
  username: string = 'Client'; // This value could be dynamic

  constructor(private chatbotService: ChatbotService) {}

  ngOnInit(): void {
    // Subscribe to incoming messages
    this.chatbotService.getMessages().subscribe((msg: ChatbotMessage) => {
      this.messages.push(msg);
    });
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
    }
  }
}
