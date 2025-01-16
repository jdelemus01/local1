import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditOfertanteComponent } from './edit-ofertante.component';

describe('EditOfertanteComponent', () => {
  let component: EditOfertanteComponent;
  let fixture: ComponentFixture<EditOfertanteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditOfertanteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditOfertanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
