import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';

@Component({
    selector: 'professional-home',
    templateUrl: '../template/professional.home.html',
    styleUrls: ['../css/app.component.css']
})

export class ProfessionalHomeComponent {
    private professional = new Professional();
    
    constructor(private professionalService: ProfessionalService) {
        this.professional = professionalService.findById(2);
    }
}
