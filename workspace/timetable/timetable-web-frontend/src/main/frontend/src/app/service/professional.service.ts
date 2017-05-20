import { Professional } from '../model/professional';
import { Injectable } from '@angular/core';

@Injectable()
export class ProfessionalService {

  constructor() { }

  getAll(): Professional[] {
    
    let p1: Professional;
    p1 = new Professional();
    p1.id = 1;
    p1.login = 'sdi';
    p1.firstname = 'TRAORE';
    p1.lastname = 'Seydou';
    p1.speciality = 'Gyneco';
    
    let p2: Professional;
    p2 = new Professional();
    p2.id = 2;
    p2.login = 'sdi2';
    p2.firstname = 'TRAORE2';
    p2.lastname = 'Seydou2';
    p2.speciality = 'Gyneco2';
    
    return [p1, p2];
  }
}
