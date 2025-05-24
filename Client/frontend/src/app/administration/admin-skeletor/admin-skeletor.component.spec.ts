import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSkeletorComponent } from './admin-skeletor.component';

describe('AdminSkeletorComponent', () => {
  let component: AdminSkeletorComponent;
  let fixture: ComponentFixture<AdminSkeletorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminSkeletorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSkeletorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
