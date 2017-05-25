import { AuthenticationService } from '../service/authentication.service';
import { Component } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'login',
  templateUrl: '../template/login.html',
  styleUrls: ['../app.component.css']
})

export class LoginComponent {
  
  public loginForm = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });
  
  constructor(private authenticationService: AuthenticationService, private formBuilder: FormBuilder) {}
  
  doLogin(event) {
    console.log(event);
    console.log(this.loginForm.value);
  }
}
