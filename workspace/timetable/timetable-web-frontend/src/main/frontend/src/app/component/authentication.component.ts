import { AuthenticationService } from '../service/authentication.service';
import { Component } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: '../template/login.html',
  styleUrls: ['../css/app.component.css', '../css/login.component.css']
})
export class LoginComponent {
  
  public loginForm = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });
  
  constructor(private router: Router, private authenticationService: AuthenticationService, private formBuilder: FormBuilder) {}
  
  doLogin(event) {
    console.log(event);
    console.log(this.loginForm.value);
  }
  
  showRegistrationComponent(): void {
      this.router.navigate(['/professionalRegistrationExt']);
  }
}
