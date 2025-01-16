import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainOferComponent } from './main-ofer.component';

describe('MainOferComponent', () => {
  let component: MainOferComponent;
  let fixture: ComponentFixture<MainOferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainOferComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainOferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
