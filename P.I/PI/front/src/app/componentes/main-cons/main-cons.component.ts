import { Component } from '@angular/core';
import { Actividad } from '../../modelos/actividad';
import { Consumidor } from '../../modelos/consumidor';
import { ConsumoActividad } from '../../modelos/consumo-actividad';
import { ActividadService } from '../../servicios/actividad.service';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { ConsumoActConsService } from '../../servicios/consumo-act-cons.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-main-cons',
  standalone: true,
  imports: [],
  templateUrl: './main-cons.component.html',
  styleUrl: './main-cons.component.css'
})
export class MainConsComponent {

  public acts: Actividad[] = [];
    public consumidor: Consumidor = <Consumidor>{};
    public consumoActCons : ConsumoActividad[] = [];
    public disponible: boolean = true;
    public mayoriaDeEdad: boolean = false;
  
    constructor(
      private actividadService: ActividadService,
      private consumidorService: ConsumidorService,
      private ar: ActivatedRoute
    ) {
      this.consumidorService
        .getConsumidorById(this.ar.snapshot.params['id'])
        .subscribe((data) => {
          this.consumidor = data;
        });
  
      this.actividadService.getAllActividades().subscribe((data) => {
        this.acts = data;
      });
    }
}
