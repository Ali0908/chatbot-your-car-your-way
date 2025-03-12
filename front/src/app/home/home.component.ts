import {Component, OnInit} from '@angular/core';
import {ChatbotMessage} from '../model/chatbot-message';
import {ChatbotService} from '../services/chatbot.service';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Button} from 'primeng/button';
import {Panel} from 'primeng/panel';
import {InputText} from 'primeng/inputtext';
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

  constructor(private chatbotService: ChatbotService) {
  }

  ngOnInit(): void {
    // Subscribe to incoming messages
    this.chatbotService.getUserConnected().subscribe({
      next: (user: any) => {
        this.username = user?.name;
      }
    })
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
