import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: '../template/app.header.html',
  styleUrls: ['../css/app.component.css', '../css/header.component.css']
})
export class HeaderComponent {
    
    userConnected: boolean = false;

    constructor(private router: Router, private authenticationService: AuthenticationService, private sharedService: SharedService) {}
    
    showLoginComponent(): void {
        this.router.navigate(['/login']);
    }
    
    showHomeComponent(): void {
        this.router.navigate(['/home']);
    }
    
    disconnect() {
        this.authenticationService.logoff();
        this.showHomeComponent();
    }
    
    ngOnInit() {
        this.sharedService.connectedUser.subscribe(user => {
            this.userConnected = user != null;
        });
    }
}
