import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';

@Component({
    selector: 'professional-appointment',
    templateUrl: '../template/professional.home.appointment.html',
    styleUrls: ['../css/app.component.css']
})
export class ProfessionalAppointmentComponent {
    professional = new Professional();
    
    constructor(private professionalService: ProfessionalService) {}
}
