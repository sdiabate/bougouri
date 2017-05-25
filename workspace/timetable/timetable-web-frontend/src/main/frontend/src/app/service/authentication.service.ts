import { Injectable } from '@angular/core';
import {Restangular} from 'ngx-restangular';

@Injectable()
export class AuthenticationService {
  
  constructor(private restangular: Restangular) {}
}
