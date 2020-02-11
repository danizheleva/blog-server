import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TripDetailMainComponent } from './trip-detail-main.component';

describe('TripDetailMainComponent', () => {
  let component: TripDetailMainComponent;
  let fixture: ComponentFixture<TripDetailMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TripDetailMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TripDetailMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
