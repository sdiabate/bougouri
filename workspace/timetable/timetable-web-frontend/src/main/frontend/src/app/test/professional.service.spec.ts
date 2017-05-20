import { TestBed, inject } from '@angular/core/testing';

import { ProfessionalService } from '../service/professional.service';

describe('ProfessionalService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProfessionalService]
    });
  });

  it('should be created', inject([ProfessionalService], (service: ProfessionalService) => {
    expect(service).toBeTruthy();
  }));
});
