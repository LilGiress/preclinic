import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDocdorScheduleComponent } from './edit-docdor-schedule.component';

describe('EditDocdorScheduleComponent', () => {
  let component: EditDocdorScheduleComponent;
  let fixture: ComponentFixture<EditDocdorScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditDocdorScheduleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditDocdorScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
