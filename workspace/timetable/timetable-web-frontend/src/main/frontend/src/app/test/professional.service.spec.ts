import { TestBed, inject } from '@angular/core/testing';
import {Professional} from '../model/professional.model';
import { ProfessionalService } from '../service/professional.service';
import {Restangular, RestangularHttp} from 'ngx-restangular';
import {Http, HttpModule, ConnectionBackend, RequestOptions, RequestOptionsArgs} from '@angular/http';

describe('ProfessionalService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule],
      providers: [Restangular, RestangularHttp, Http, HttpModule, ConnectionBackend, RequestOptions, ProfessionalService]
    });
  });

  it('should be created', inject([ProfessionalService], (service: ProfessionalService) => {
    expect(service).toBeTruthy();
  }));
  
  describe('#register(professional)', () => {
    it('should create a professional', inject([ProfessionalService], (service: ProfessionalService) => {
      let p1 = new Professional();
      p1.id = 1;
      p1.login = 'sdi';
      p1.firstname = 'TRAORE';
      p1.lastname = 'Seydou';
      p1.speciality = 'Gyneco';
      expect(service.register(p1).exceptionCode).toEqual(null);
    }));
  });

});
