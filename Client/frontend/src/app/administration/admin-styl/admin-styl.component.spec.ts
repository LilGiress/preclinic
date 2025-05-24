import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminStylComponent } from './admin-styl.component';

describe('AdminStylComponent', () => {
  let component: AdminStylComponent;
  let fixture: ComponentFixture<AdminStylComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminStylComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminStylComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
