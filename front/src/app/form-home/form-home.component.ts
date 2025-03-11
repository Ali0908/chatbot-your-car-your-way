import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button'
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {Router} from '@angular/router';
import {ChatbotService} from '../services/chatbot.service';


@Component({
  selector: 'app-form-home',
  imports: [
    ButtonModule,
    FormsModule,
    InputText,
    ReactiveFormsModule
  ],
  templateUrl: './form-home.component.html',
  styleUrl: './form-home.component.scss'
})
export class FormHomeComponent implements OnInit {
  form!: FormGroup;
  disabled: boolean = true;

  constructor(private fb: FormBuilder,
              private router: Router,
              private chatbotService: ChatbotService) {
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
  const modifiedUser =  this.form.value as string;
  this.chatbotService.sendName(modifiedUser).subscribe();
    this.router.navigate(['']).then();
  }
}
