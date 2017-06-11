import { Injectable } from '@angular/core';

@Injectable()
export class SharedService {
    public currentRouteUrl: string;
    public previousRouteUrl: string;
}