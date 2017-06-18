import { Injectable } from '@angular/core';
import {Restangular} from 'ngx-restangular';
import { SharedService } from '../service/shared.service';

@Injectable()
export class AuthenticationService {
    
    constructor(private restangular: Restangular, private sharedService: SharedService) {}
    
    login(user: string, password: string): boolean {
        this.logoff();
        if(user == "admin" && password == "admin") {
            this.sharedService.connectedUser.next(user);
            return true;
        }
        return false;
    }
    
    logoff(): void {
        this.sharedService.connectedUser.next(null);
    }
    
}
