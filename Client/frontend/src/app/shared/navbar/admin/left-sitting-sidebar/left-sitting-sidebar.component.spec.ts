import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeftSittingSidebarComponent } from './left-sitting-sidebar.component';

describe('LeftSittingSidebarComponent', () => {
  let component: LeftSittingSidebarComponent;
  let fixture: ComponentFixture<LeftSittingSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeftSittingSidebarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeftSittingSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
