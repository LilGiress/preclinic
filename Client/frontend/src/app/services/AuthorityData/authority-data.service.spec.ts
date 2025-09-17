import { TestBed } from '@angular/core/testing';

import { AuthorityDataService } from './authority-data.service';

describe('AuthorityDataService', () => {
  let service: AuthorityDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthorityDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
