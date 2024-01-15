import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TauxComponent } from './taux.component';

describe('TauxComponent', () => {
  let component: TauxComponent;
  let fixture: ComponentFixture<TauxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TauxComponent]
    });
    fixture = TestBed.createComponent(TauxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
