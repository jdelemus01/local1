import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaUsusariosComponent } from './lista-ususarios.component';

describe('ListaUsusariosComponent', () => {
  let component: ListaUsusariosComponent;
  let fixture: ComponentFixture<ListaUsusariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaUsusariosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaUsusariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
