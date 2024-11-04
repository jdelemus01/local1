import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

import { Actividad } from '../../modelos/actividad';
import { Ofertante } from '../../modelos/ofertante';
import { Tipo } from '../../modelos/tipo';
import { ActividadService } from '../../servicios/actividad.service';

@Component({
  selector: 'app-lista-actividades',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './lista-actividades.component.html',
  styleUrl: './lista-actividades.component.css',
})
export class ListaActividadesComponent {
  
  //Variables-----------------------------------------------------------------------------------------------
  public ofertante: Ofertante = <Ofertante>{};
  public tipo: Tipo = <Tipo>{};
  public acts: Actividad[] = [];


  //Constructor-----------------------------------------------------------------------------------------------
  constructor(private service: ActividadService) {
    this.service.getAllActividades().subscribe((data) => {
      this.acts = data;
    });
  }


  //Métodos-----------------------------------------------------------------------------------------------
  public borrarActividad(id: number) {
    if (confirm('¿Seguro de que desea borrar a ' + id)) {
      this.service.deleteActividad(id).subscribe({
        next: data => this.acts = data,
        error: err => console.error('No se pudo realizar la operación.', err),
      });
    }
  }
}
