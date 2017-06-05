import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: '../template/app.header.html',
  styleUrls: ['../css/app.component.css', '../css/header.component.css']
})
export class HeaderComponent {

    constructor(private router: Router) {}
    
    showLoginComponent(): void {
        this.router.navigate(['/login']);
    }
    
    showHomeComponent(): void {
        this.router.navigate(['/home']);
    }
}
