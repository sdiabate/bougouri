import { Component, ViewChild } from '@angular/core';
import { ProfessionalRegistrationComponent } from './professional.registration.component';

@Component({
  selector: 'professional-registration-modal',
  templateUrl: '../template/professional.registration.modal.html',
  styleUrls: ['../css/app.component.css', '../css/header.component.css']
})
export class ProfessionalRegistrationModalComponent {
    
    @ViewChild(ProfessionalRegistrationComponent) registrationComponent: ProfessionalRegistrationComponent;
    
    public register(): void {
        this.registrationComponent.register();
    }
    
    public cancel(): void {
        this.registrationComponent.cancel();
    }
}
