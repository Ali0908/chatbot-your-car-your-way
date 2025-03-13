import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button'
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {ChatbotService} from '../services/chatbot.service';
import {MatButton} from '@angular/material/button';
import {MatFormField} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';


@Component({
  selector: 'app-form-home',
  imports: [
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatButton,
    MatFormField,
    MatInput,
  ],
  templateUrl: './form-home.component.html',
  styleUrl: './form-home.component.scss'
})
export class FormHomeComponent implements OnInit {
  form!: FormGroup;

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
  const modifiedUser =  this.form.value;
  this.chatbotService.sendName(modifiedUser).subscribe();
    this.router.navigate(['home']).then();
  }
}
