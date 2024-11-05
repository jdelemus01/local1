import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Actividad } from '../../modelos/actividad';
import { Tipo } from '../../modelos/tipo';
import { Ofertante } from '../../modelos/ofertante';
import { ActividadService } from '../../servicios/actividad.service';
import { TipoService } from '../../servicios/tipo.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { OfertanteService } from '../../servicios/ofertante.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-actividad-form',
  standalone: true,
  imports: [FormsModule, RouterLink, CommonModule],
  templateUrl: './actividad-form.component.html',
  styleUrl: './actividad-form.component.css',
})
export class ActividadFormComponent {
  //Variables-----------------------------------------------------------------------------------------------
  public act: Actividad = <Actividad>{};
  public tipos: Tipo[] = [];
  public ofertantes: Ofertante[] = [];
  public idActividad: number;

  //Constructor-----------------------------------------------------------------------------------------------
  constructor(
    private actividadService: ActividadService,
    private tipoService: TipoService,
    private ofertanteService: OfertanteService,
    private ar: ActivatedRoute,
    private ruta: Router
  ) {
    this.idActividad = ar.snapshot.params['id'];

    this.ofertanteService.getAllOfertantes().subscribe((data) => {
      this.ofertantes = data;
    });

    this.tipoService.getAllTipos().subscribe((data) => {
      this.tipos = data;
    });

    this.actividadService.getActividad(this.idActividad).subscribe((data) => {
      this.act = data;
    });
  }

  //Métodos-----------------------------------------------------------------------------------------------
  public modificarActividad(actUpdate: Actividad) {
    this.actividadService.updateActividad(actUpdate).subscribe();
    //this.ruta.navigate(['actividades']);
  }

  public volver() {
    this.ruta.navigate(['actividades']);
  }
}
