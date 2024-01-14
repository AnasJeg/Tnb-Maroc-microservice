import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaxeComponent } from './taxe.component';

describe('TaxeComponent', () => {
  let component: TaxeComponent;
  let fixture: ComponentFixture<TaxeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TaxeComponent]
    });
    fixture = TestBed.createComponent(TaxeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
