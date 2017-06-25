import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { ProfessionalPlanningComponent } from './professional.home.planning.component';
import { ProfessionalAppointmentComponent } from './professional.home.appointment.component';
import { ProfessionalHolidayComponent } from './professional.home.holiday.component';
import { Component, ViewChild } from '@angular/core';

@Component({
    selector: 'professional-home',
    templateUrl: '../template/professional.home.html',
    styleUrls: ['../css/app.component.css']
})
export class ProfessionalHomeComponent {
    private professional = new Professional();
    
    @ViewChild(ProfessionalPlanningComponent) planningComponent: ProfessionalPlanningComponent;
    @ViewChild(ProfessionalAppointmentComponent) ProfessionalAppointmentComponent: ProfessionalAppointmentComponent;
    @ViewChild(ProfessionalHolidayComponent) holidayComponent: ProfessionalHolidayComponent;
    
    constructor(private professionalService: ProfessionalService) {
    }
    
    ngOnInit() {
        this.planningComponent.professional = this.professional;
        this.ProfessionalAppointmentComponent.professional = this.professional;
        this.holidayComponent.professional = this.professional;
    }
}
