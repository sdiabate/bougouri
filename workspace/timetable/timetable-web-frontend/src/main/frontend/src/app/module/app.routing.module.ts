import { NgModule }             from '@angular/core';
import { RouterModule, Routes, Router, NavigationEnd } from '@angular/router';
import { HomeComponent } from '../component/app.home.component';
import { LoginComponent } from '../component/authentication.component';
import { ContactComponent } from '../component/contact.component';
import { ProfessionalRegistrationExtComponent } from '../component/professional.registration.ext.component';
import { SharedService } from '../service/shared.service';

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
      path: 'home',
      component: HomeComponent,
  },
  {
      path: 'contact',
      component: ContactComponent,
  },
  {
      path: 'professionalRegistrationExt',
      component: ProfessionalRegistrationExtComponent,
  },
  { 
    path: '',  
    redirectTo: 'home',
    pathMatch: 'full' 
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class AppRoutingModule {
    
    constructor(private router: Router, private sharedService: SharedService) {
        this.subscribeToRouteChange();
    }
    
    private subscribeToRouteChange(): void {
        this.router.events
        .filter(event => event instanceof NavigationEnd)
        .subscribe(e => {
            this.sharedService.previousRouteUrl = this.sharedService.currentRouteUrl;
            this.sharedService.currentRouteUrl = (<NavigationEnd> e).url;
            console.log('current:', this.sharedService.currentRouteUrl);
            console.log('previous:', this.sharedService.previousRouteUrl);
        });
    }
}
