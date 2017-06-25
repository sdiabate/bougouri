import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';

@Component({
    selector: 'professional-holiday',
    templateUrl: '../template/professional.home.holiday.html',
    styleUrls: ['../css/app.component.css']
})
export class ProfessionalHolidayComponent {
    professional = new Professional();
    
    constructor(private professionalService: ProfessionalService) {}
}
