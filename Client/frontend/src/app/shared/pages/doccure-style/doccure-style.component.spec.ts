import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoccureStyleComponent } from './doccure-style.component';

describe('DoccureStyleComponent', () => {
  let component: DoccureStyleComponent;
  let fixture: ComponentFixture<DoccureStyleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoccureStyleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoccureStyleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
