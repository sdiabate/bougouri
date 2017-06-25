import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';

@Component({
    selector: 'professional-planning',
    templateUrl: '../template/professional.home.planning.html',
    styleUrls: ['../css/app.component.css']
})
export class ProfessionalPlanningComponent {
    professional = new Professional();
    
    constructor(private professionalService: ProfessionalService) {}
}
