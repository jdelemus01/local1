import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ActividadService } from '../../servicios/actividad.service';
import { Actividad } from '../../modelos/actividad';
import { TipoService } from '../../servicios/tipo.service';
import { Tipo } from '../../modelos/tipo';
import { Ofertante } from '../../modelos/ofertante';
import { OfertanteService } from '../../servicios/ofertante.service';

@Component({
  selector: 'app-actividad-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './actividad-form.component.html',
  styleUrl: './actividad-form.component.css',
})
export class ActividadFormComponent {
  public actividad: Actividad = <Actividad>{};
  public tipos: Tipo[] = [];
  public ofertante : Ofertante = <Ofertante>{};

  constructor(
    private actividadService: ActividadService,
    private tipoService: TipoService,
    private ofertanteService: OfertanteService,
    private ar: ActivatedRoute
  ) {
    this.actividad.id = this.ar.snapshot.params['id'];

    this.ofertanteService.getOfertanteById(this.ar.snapshot.params["idO"]).subscribe(
      data => {
        this.actividad.ofertante = data;
      }
    )

    this.tipoService.getAllTipos().subscribe(
      data => {
        this.tipos = data;
        this.actividad.tipo = data[0];
      }
    )
  }

  onSubmit(actRecibida: Actividad) {
    
    if(actRecibida.aireLibre == undefined){
      actRecibida.aireLibre = false;
    }
    if(actRecibida.mas18 == undefined){
      actRecibida.mas18 = true;
    } else {
      actRecibida.mas18 = false;
    }
    
    this.actividadService.insertActividad(actRecibida).subscribe();
  }
}
