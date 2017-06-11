import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RestangularModule } from 'ngx-restangular';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { DateTimePickerModule} from 'ngx-datetime-picker';
import { NgxDateRangePickerModule } from 'ngx-daterangepicker';
import {ModalModule} from "ngx-modal";
import { AppComponent } from '../component/app.component';
import { HomeComponent } from '../component/app.home.component';
import { LoginComponent } from '../component/authentication.component';
import { ProfessionalRegistrationComponent } from '../component/professional.registration.component';
import { ProfessionalRegistrationModalComponent } from '../component/professional.registration.modal.component';
import { TimeSlotComponent } from '../component/time.slot.component';
import { TimeIntervalComponent } from '../component/time.interval.component';
import { WorkingDayComponent } from '../component/working.day.component';
import { HeaderComponent } from '../component/app.header.component';
import { LeftComponent } from '../component/app.left.component';
import { CenterComponent } from '../component/app.center.component';
import { FooterComponent } from '../component/app.footer.component';
import { ContactComponent } from '../component/contact.component';
import { SearchComponent } from '../component/search.min.component';
import { ProfessionalRegistrationExtComponent } from '../component/professional.registration.ext.component';
import { AuthenticationService } from '../service/authentication.service';
import { ProfessionalService } from '../service/professional.service';
import { AppRoutingModule }        from '../module/app.routing.module';
import { Router } from '@angular/router';

export function restangularConfigFactory (RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:8080/timetable');
  // RestangularProvider.setDefaultHeaders({'Authorization': 'Bearer UDXPx-Xko0w4BRKajozCVy20X11MRZs1'});
}

@NgModule({
  declarations: [
    HomeComponent,
    AppComponent,
    LoginComponent,
    ProfessionalRegistrationComponent,
    ProfessionalRegistrationModalComponent,
    WorkingDayComponent,
    TimeSlotComponent,
    TimeIntervalComponent,
    HeaderComponent,
    LeftComponent,
    CenterComponent,
    FooterComponent,
    ContactComponent,
    SearchComponent,
    ProfessionalRegistrationExtComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    RestangularModule.forRoot(restangularConfigFactory),
    ButtonsModule.forRoot(),
    DateTimePickerModule,
    ModalModule,
    NgxDateRangePickerModule,
    AppRoutingModule
  ],
  providers: [AuthenticationService, ProfessionalService],
  bootstrap: [
    AppComponent
  ]
})
  
export class AppModule {
    constructor(router: Router) {
        console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
      }
}
