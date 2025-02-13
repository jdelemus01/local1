import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Ofertante } from '../../modelos/ofertante';
import { Consumidor } from '../../modelos/consumidor';
import { AuthService } from '../../servicios/auth.service';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {

  public ofertante: Ofertante = {
    id: -1,
    email: "",
    usuario: {
      id: 0,
      nombre: "",
      apellidos: "",
      fechaNacimiento: new Date,
      username: "",
      password: ""
    },
    actividades: []
  };

  public rol: string = "cons";

  constructor(
    private loginService : AuthService,
    private router : Router
  ) {

  }

  mandarCreds(valores: NgForm) {
    console.log(valores.value);
    if (valores.value.tipoUsuario == "ofe") {
      let ofertante: Ofertante = {
        id: -1,
        email: valores.value.email,
        usuario: {
          id: -1,
          nombre: valores.value.nombre,
          apellidos: valores.value.apellidos,
          fechaNacimiento: valores.value.fechaNacimiento,
          username: valores.value.username,
          password: valores.value.contrasenya
        },
        actividades : []
      }
      this.loginService.registrarOfertante(ofertante).subscribe({
        next: data => {console.log(data);
                        this.router.navigate(["/login"]);
        }
      }
      );
    } else {
      let consumidor: Consumidor = {
        id: -1,
        email: valores.value.email,
        usuario: {
          id: -1,
          nombre: valores.value.nombre,
          apellidos: valores.value.apellidos,
          fechaNacimiento: valores.value.fechaNacimiento,
          username: valores.value.username,
          password: valores.value.contrasenya
        }
      }
      this.loginService.registrarConsumidor(consumidor).subscribe({
        next: data => {this.router.navigate(["/login"]);
          console.log(data)
        }
      }
      );
    }
  }
}
