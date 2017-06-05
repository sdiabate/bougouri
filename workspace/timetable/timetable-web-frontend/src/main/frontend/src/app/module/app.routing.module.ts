import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../component/app.home.component';
import { LoginComponent } from '../component/authentication.component';
import { ContactComponent } from '../component/contact.component';

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
      path: 'home',
      component: HomeComponent,
  },
  {
      path: 'contact',
      component: ContactComponent,
  },
  { 
    path: '',  
    redirectTo: 'home', 
    pathMatch: 'full' 
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class AppRoutingModule { }
