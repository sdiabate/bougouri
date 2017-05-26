import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RestangularModule } from 'ngx-restangular';
import { AppComponent } from './app.component';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { LoginComponent } from './component/authentication.component';
import { ProfessionalRegistrationComponent } from './component/professional.registration.component';
import { TimeSlotComponent } from './component/time.slot.component';
import { AuthenticationService } from './service/authentication.service';
import { ProfessionalService } from './service/professional.service';

export function restangularConfigFactory (RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:8080/timetable');
  // RestangularProvider.setDefaultHeaders({'Authorization': 'Bearer UDXPx-Xko0w4BRKajozCVy20X11MRZs1'});
}

@NgModule({
  declarations: [
    LoginComponent,
    ProfessionalRegistrationComponent,
    TimeSlotComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RestangularModule.forRoot(restangularConfigFactory),
    ButtonsModule.forRoot()
  ],
  providers: [AuthenticationService, ProfessionalService],
  bootstrap: [LoginComponent, ProfessionalRegistrationComponent, TimeSlotComponent]
})
  
export class AppModule { }
