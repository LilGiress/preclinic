import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkeletorSettingComponent } from './skeletor-setting.component';

describe('SkeletorSettingComponent', () => {
  let component: SkeletorSettingComponent;
  let fixture: ComponentFixture<SkeletorSettingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SkeletorSettingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkeletorSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
