import { Professional } from '../model/professional.model';
import { Injectable } from '@angular/core';
import {Restangular} from 'ngx-restangular';

@Injectable()
export class ProfessionalService {

  constructor(private restangular: Restangular) {}
  
  findById(id: number): Professional {
    const professionals: Professional[] = this.getAll();
    return professionals.find(p => p.id === id);
  }
    
  register(professional: Professional): any {
    const result: any = this.restangular.all('professional/registerProfessional').post(professional);
    console.log(result);
    return result;
  }
    
  getAll(): Professional[] {
    
    const p1 = new Professional();
    p1.id = 1;
    p1.login = 'sdi';
    p1.firstname = 'TRAORE';
    p1.lastname = 'Seydou';
    p1.specialty = 'Gyneco';
    
    const p2 = new Professional();
    p2.id = 2;
    p2.login = 'sdi2';
    p2.firstname = 'TRAORE2';
    p2.lastname = 'Seydou2';
    p2.specialty = 'Gyneco2';
    
    return [p1, p2];
  }
}
