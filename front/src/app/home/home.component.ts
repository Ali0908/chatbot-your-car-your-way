import {Component, OnInit} from '@angular/core';
import {ChatbotMessage} from '../model/chatbot-message';
import {ChatbotService} from '../services/chatbot.service';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Button, ButtonDirective, ButtonLabel} from 'primeng/button';
import {Panel} from 'primeng/panel';
import {InputText} from 'primeng/inputtext';

@Component({
  selector: 'app-home',
  imports:  [CommonModule, FormsModule, DatePipe, ButtonDirective, Panel, ButtonLabel, InputText, Button],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  messages: ChatbotMessage[] = [];
  newMessage: string = '';
  username: string = 'Client'; // This value could be dynamic

  constructor(private chatbotService: ChatbotService) {
  }

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
