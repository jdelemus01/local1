import { Component } from '@angular/core';
import { ActividadService } from '../../servicios/actividad.service';
import { Actividad } from '../../modelos/actividad';
import { Consumidor } from '../../modelos/consumidor';
import { ConsumoActividad } from '../../modelos/consumo-actividad';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { ConsumoActConsService } from '../../servicios/consumo-act-cons.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-actividades-cons',
  standalone: true,
  imports: [],
  templateUrl: './actividades-cons.component.html',
  styleUrl: './actividades-cons.component.css',
})
export class ActividadesConsComponent {
  public acts: Actividad[] = [];
  public consumidor: Consumidor = <Consumidor>{};
  public consumoActCons : ConsumoActividad[] = [];
  public disponible: boolean = true;
  public mayoriaDeEdad: boolean = false;

  constructor(
    private actividadService: ActividadService,
    private consumidorService: ConsumidorService,
    private consumoActConsService: ConsumoActConsService,
    private ar: ActivatedRoute
  ) {
    this.consumidorService
      .getConsumidorById(this.ar.snapshot.params['id'])
      .subscribe((data) => {
        this.consumidor = data;
        this.mayoriaDeEdad = this.calcularMas18(data.fechaNacimiento);
      });

    this.actividadService.getAllActividades().subscribe((data) => {
      this.acts = data;
    });

    this.consumoActConsService.getConsumoByConsumidor(this.ar.snapshot.params['id']).subscribe(
      (data) => {
        console.log(data);
        this.consumoActCons = data;
      }
    );
  }
    calcularMas18(fechaNacimiento: Date): boolean {
      const fechaActual = new Date();
      const fechaNacimientoDate = new Date(fechaNacimiento);
      let edad = fechaActual.getFullYear() - fechaNacimientoDate.getFullYear();

      const mesActual = fechaActual.getMonth();
      const diaActual = fechaActual.getDate();
      const mesNacimiento = fechaNacimientoDate.getMonth();
      const diaNacimiento = fechaNacimientoDate.getDate();

      if (
        mesActual < mesNacimiento ||
        (mesActual == mesNacimiento && diaActual < diaNacimiento)
      ) {
        edad--;
      }

      return edad >= 18;
    }

    consultarInscripcion(idAct: number) {
      const encontrado = this.consumoActCons.find(
        (consAct) => consAct.actividad.id === idAct
      );
      return encontrado;
    }

    
    insertarInscripcion(act : Actividad){
        let consumo : ConsumoActividad = {
        consumidor : this.consumidor,
        actividad : act
      };

      this.consumoActConsService.insertConsumo(consumo).subscribe(
        (data) => {
          this.consumoActCons = data;
        }
      );
    }
    
    eliminarInscripcion(idAct : number){
      this.consumoActConsService.deleteConsumo(idAct, this.consumidor.id).subscribe(
        (data) => {
          this.consumoActCons = data;
        }
      );
    }
  }
