import { Professional } from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import { Component } from '@angular/core';

@Component({
  selector: 'professional-registration',
  templateUrl: '../template/professional.registration.html',
  styleUrls: ['../app.component.css']
})

export class ProfessionalRegistrationComponent {
  private professional = new Professional();
  
  constructor(private professionalService: ProfessionalService) {
    this.professional = professionalService.findById(2);
  }
  
  public register(): void {
    console.log(this.professional);
  }
}
