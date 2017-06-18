import { Injectable } from '@angular/core';
import { ReplaySubject } from 'rxjs/ReplaySubject';

@Injectable()
export class SharedService {
    
    connectedUser = new ReplaySubject<string>(1);

    currentRouteUrl: string;
    previousRouteUrl: string;
}