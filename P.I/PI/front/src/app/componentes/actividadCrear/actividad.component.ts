import { Component } from '@angular/core';
import { ActividadService } from '../../servicios/actividad.service';
import { Actividad } from '../../modelos/actividad';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Tipo } from '../../modelos/tipo';
import { Ofertante } from '../../modelos/ofertante';
import { TipoService } from '../../servicios/tipo.service';
import { OfertanteService } from '../../servicios/ofertante.service';

@Component({
  selector: 'app-actividad',
  standalone: true,
  imports: [DatePipe, FormsModule],
  templateUrl: './actividad.component.html',
  styleUrl: './actividad.component.css',
})
export class ActividadComponent {
  //Variables-----------------------------------------------------------------------------------------------
  public act: Actividad = <Actividad>{};
  public tipos: Tipo[] = [];
  public ofertantes: Ofertante[] = [];

  //Constructor-----------------------------------------------------------------------------------------------
  constructor(
    private actividadService: ActividadService,
    private tipoService: TipoService,
    private ofertanteService: OfertanteService,
    private ar: ActivatedRoute,
    private ruta: Router
  ) {
    this.act.id = -1;

    this.ofertanteService.getAllOfertantes().subscribe((data) => {
      this.ofertantes = data;
      this.act.ofertante = this.ofertantes[0];
    });

    this.tipoService.getAllTipos().subscribe((data) => {
      this.tipos = data;
      this.act.tipo = this.tipos[0];
    });
  }

  //Métodos-----------------------------------------------------------------------------------------------
  public insertarActividad(actIns: Actividad) {
    console.log('Actividad a insertar: ' + actIns);

    this.actividadService.insertActividad(actIns).subscribe();
    //this.ruta.navigate(['adminActs']);
  }
}
