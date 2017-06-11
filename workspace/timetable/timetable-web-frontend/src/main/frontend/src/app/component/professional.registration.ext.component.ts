import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';

@Component({
    selector: 'professional-registration-ext',
    templateUrl: '../template/professional.registration.ext.html',
    styleUrls: ['../css/app.component.css', '../css/professional.registration.ext.component.css']
})

export class ProfessionalRegistrationExtComponent {
    private professional = new Professional();
    
    constructor(private professionalService: ProfessionalService) {
        this.professional = professionalService.findById(2);
    }
    
    public register(): void {
        console.log(this.professional);
    }
    
    public cancel(): void {
        console.log('Operation cancelled !');
    }
}
