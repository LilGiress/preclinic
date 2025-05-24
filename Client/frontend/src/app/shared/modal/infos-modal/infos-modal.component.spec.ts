import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfosModalComponent } from './infos-modal.component';

describe('InfosModalComponent', () => {
  let component: InfosModalComponent;
  let fixture: ComponentFixture<InfosModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfosModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfosModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
