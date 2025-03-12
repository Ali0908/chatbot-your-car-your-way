import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe, CommonModule } from '@angular/common';
import {Button, ButtonDirective, ButtonLabel} from 'primeng/button';
import { ChatbotMessage } from './model/chatbot-message';
import { ChatbotService } from './services/chatbot.service';
import { Panel } from 'primeng/panel';
import { InputText } from 'primeng/inputtext';
import {FormHomeComponent} from './form-home/form-home.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
}
