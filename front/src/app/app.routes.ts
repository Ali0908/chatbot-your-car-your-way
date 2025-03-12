import { Routes } from '@angular/router';
import {FormHomeComponent} from './form-home/form-home.component';
import {HomeComponent} from './home/home.component';

export const routes: Routes = [
  {path: '', redirectTo: '/form-home', pathMatch: 'full'},
  {title: 'Home-Form', path: 'form-home', component: FormHomeComponent},
  {title: 'Home', path: 'home', component: HomeComponent},

];

