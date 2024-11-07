import { Component } from '@angular/core';
import { ActividadService } from '../../servicios/actividad.service';
import { Actividad } from '../../modelos/actividad';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-actividades-ofer',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './actividades-ofer.component.html',
  styleUrl: './actividades-ofer.component.css'
})
export class ActividadesOferComponent {

  public acts : Actividad [] = [];
  public idOf : number; 

  constructor(private actService: ActividadService, private ar: ActivatedRoute) {
    this.idOf = this.ar.snapshot.params["id"];

    this.actService.getActividadesByOfertante(this.idOf).subscribe(
      data => {
        this.acts = data;
      }
    );
  }
}
