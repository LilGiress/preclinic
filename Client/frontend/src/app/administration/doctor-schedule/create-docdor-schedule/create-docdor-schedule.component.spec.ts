import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDocdorScheduleComponent } from './create-docdor-schedule.component';

describe('CreateDocdorScheduleComponent', () => {
  let component: CreateDocdorScheduleComponent;
  let fixture: ComponentFixture<CreateDocdorScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateDocdorScheduleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateDocdorScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
