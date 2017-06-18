import { AuthenticationService } from '../service/authentication.service';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
    selector: 'login',
    templateUrl: '../template/login.html',
    styleUrls: ['../css/app.component.css', '../css/login.component.css']
})
export class LoginComponent {
    
    loginSuccess: boolean = false;
    loginTried: boolean = false;

    loginForm = this.formBuilder.group({
        login: '',
        password: ''
    });
    
    constructor(private router: Router, private authenticationService: AuthenticationService, private formBuilder: FormBuilder) {
        this.loginForm.valueChanges.subscribe(data => {
            this.loginTried = false;
        });
    }
    
    doLogin() {
        console.log(this.loginForm.value);
        
        if(!this.formFilled()) {
            return;
        }
        
        this.loginTried = true;
        
        if(this.authenticationService.login(this.loginForm.value.login, this.loginForm.value.password)) {
            this.loginSuccess = true;
            this.router.navigate(['/professionalHome']);
        } else {
            this.loginSuccess = false;
        }
    }
    
    showRegistrationComponent(): void {
        this.router.navigate(['/professionalRegistrationExt']);
    }
    
    formFilled(): boolean {
        return this.loginForm.value.login != '' && this.loginForm.value.password != '';
    }
}
