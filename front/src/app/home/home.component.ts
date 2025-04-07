import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ChatbotMessage} from '../model/chatbot-message';
import {ChatbotService} from '../services/chatbot.service';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatFormField} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {from, of} from 'rxjs';
import {switchMap} from 'rxjs/operators';

@Component({
    selector: 'app-home',
    imports: [CommonModule, FormsModule, DatePipe, MatButton, MatFormField, MatInput, ReactiveFormsModule],
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

    messages: ChatbotMessage[] = [];
    newMessage: string = '';
    username!: string;

    constructor(private chatbotService: ChatbotService, private cd: ChangeDetectorRef) {
    }

    ngOnInit(): void {
        this.username = localStorage.getItem('username') || '';
        this.getMessages();
    }


    getMessages() {
        this.chatbotService.getMessages().subscribe({
            next: (msg: ChatbotMessage[]) => {
                this.messages = msg;
                this.cd.detectChanges();
            },
            error: () => {
            }
        });
    }

    send() {
        if (this.newMessage.trim()) {
            this.username = localStorage.getItem('username') || '';
            const message: ChatbotMessage = {
                sender: this.username,
                content: this.newMessage,
                timestamp: new Date()
            };
            if (this.username) {
                this.chatbotService.sendMessage(message);
                this.newMessage = '';
                this.chatbotService.messageSubject.subscribe({
                    next: (msg: ChatbotMessage) => {
                        this.messages.push(msg);
                        console.log('Message envoyé:', this.messages);
                        this.cd.detectChanges();
                    }
                });
            }
        }
    }
    // private async getUsername(): Promise<void> {
    //     return new Promise<void>((resolve, reject) => {
    //         let allUsers: any[] = [];
    //         this.chatbotService.getAllUsers().subscribe({
    //             next: (users: any[]) => {
    //                 allUsers = users;
    //
    //                 if (!allUsers || allUsers.length < 2) {
    //                     this.username = localStorage.getItem('username') || '';
    //                     console.log('Utilisation du username du localStorage', this.username);
    //                 } else {
    //                     if (allUsers[0].name === this.username) {
    //                         this.username = allUsers[1].name;
    //                         console.log('Username changé vers le deuxième utilisateur', this.username);
    //                     } else {
    //                         this.username = allUsers[0].name;
    //                         console.log('Username changé vers le premier utilisateur', this.username);
    //                     }
    //                 }
    //                 resolve();
    //             },
    //             error: (err) => {
    //                 console.error('Erreur lors de la récupération des utilisateurs', err);
    //                 reject(err);
    //             }
    //         });
    //     });
    // }

}