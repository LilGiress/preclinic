import { TestBed } from '@angular/core/testing';

import { ServiceConfigService } from './service-config.service';

describe('ServiceConfigService', () => {
  let service: ServiceConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
