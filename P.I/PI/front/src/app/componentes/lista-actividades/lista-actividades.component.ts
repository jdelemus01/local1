import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

import { Actividad } from '../../modelos/actividad';
import { Ofertante } from '../../modelos/ofertante';
import { Tipo } from '../../modelos/tipo';

@Component({
  selector: 'app-lista-actividades',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './lista-actividades.component.html',
  styleUrl: './lista-actividades.component.css'
})
export class ListaActividadesComponent {

  public ofertante: Ofertante = <Ofertante>{};
  public tipo : Tipo = <Tipo>{};

  public acts: Actividad[] = [
    {
      id: 1,
      nombre: "Excursión en montaña",
      duracion: 3.5,
      precio: 50.0,
      aireLibre: true,
      fecha: new Date(2024,9,31),
      mas18: false,
      ofertante: this.ofertante,
      tipo: this.tipo
    },
    {
      id: 2,
      nombre: "Copeo nocturno",
      duracion: 5.0,
      precio: 90.0,
      aireLibre: true,
      fecha: new Date(2024,9,31),
      mas18: true,
      ofertante: this.ofertante,
      tipo: this.tipo
    }
  ];
}
