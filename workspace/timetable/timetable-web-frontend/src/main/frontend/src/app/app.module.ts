import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RestangularModule } from 'ngx-restangular';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { DateTimePickerModule} from 'ngx-datetime-picker';
import { NgxDateRangePickerModule } from 'ngx-daterangepicker';
import { LoginComponent } from './component/authentication.component';
import { ProfessionalRegistrationComponent } from './component/professional.registration.component';
import { TimeSlotComponent } from './component/time.slot.component';
import { TimeIntervalComponent } from './component/time.interval.component';
import { WorkingDayComponent } from './component/working.day.component';
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
    WorkingDayComponent,
    TimeSlotComponent,
    TimeIntervalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RestangularModule.forRoot(restangularConfigFactory),
    ButtonsModule.forRoot(),
    DateTimePickerModule,
    NgxDateRangePickerModule
  ],
  providers: [AuthenticationService, ProfessionalService],
  bootstrap: [LoginComponent, ProfessionalRegistrationComponent, WorkingDayComponent, TimeSlotComponent, TimeIntervalComponent]
})
  
export class AppModule { }
