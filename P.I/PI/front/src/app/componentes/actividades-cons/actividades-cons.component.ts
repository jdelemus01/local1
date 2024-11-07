import { Component } from '@angular/core';
import { ActividadService } from '../../servicios/actividad.service';
import { Actividad } from '../../modelos/actividad';
import { Consumidor } from '../../modelos/consumidor';
import { ConsumidorService } from '../../servicios/consumidor.service';
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

  calcularMas18(fechaNac: Date) {
    function esMayorDeEdad(fechaNacimiento: Date): boolean {
      const fechaActual = new Date();
      let edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();

      const mesActual = fechaActual.getMonth();
      const diaActual = fechaActual.getDate();
      const mesNacimiento = fechaNacimiento.getMonth();
      const diaNacimiento = fechaNacimiento.getDate();

      if (
        mesActual < mesNacimiento ||
        (mesActual === mesNacimiento && diaActual < diaNacimiento)
      ) {
        edad--;
      }

      return edad >= 18;
    }
  }
}
