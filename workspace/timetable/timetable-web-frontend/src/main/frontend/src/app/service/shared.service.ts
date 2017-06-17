import { Injectable } from '@angular/core';

@Injectable()
export class SharedService {
    
    private _connectedUser: string;

    public currentRouteUrl: string;
    public previousRouteUrl: string;

    get connectedUser(): string {
        return this._connectedUser;
    }
    
    set connectedUser(user: string) {
        this._connectedUser = user;
    }
}