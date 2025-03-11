import { Routes } from '@angular/router';
import {FormHomeComponent} from './form-home/form-home.component';
import {HomeComponent} from './home/home.component';

export const routes: Routes = [

  {path: 'form-home', component: FormHomeComponent},
  {path: '', component: HomeComponent},

];

