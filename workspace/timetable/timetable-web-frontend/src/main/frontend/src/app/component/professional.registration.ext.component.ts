import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../service/shared.service';

@Component({
    selector: 'professional-registration-ext',
    templateUrl: '../template/professional.registration.ext.html',
    styleUrls: ['../css/app.component.css', '../css/professional.registration.ext.component.css']
})

export class ProfessionalRegistrationExtComponent {
    private professional = new Professional();
    
    constructor(private professionalService: ProfessionalService, private sharedService: SharedService, private router: Router) {
        this.professional = professionalService.findById(2);
    }
    
    public register(): void {
        console.log(this.professional);
    }
    
    public cancel(): void {
        console.log('Operation cancelled !');
        console.log('previous:', this.sharedService.previousRouteUrl);
        if(this.sharedService.previousRouteUrl != null) {
            this.router.navigate([this.sharedService.previousRouteUrl]);
        }
    }
}
