import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Consumidor } from '../../modelos/consumidor';
import { AuthService } from '../../servicios/auth.service';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../../servicios/usuario.service';
import { Usuario } from '../../modelos/usuario';

@Component({
  selector: 'app-edit-consumidor',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './edit-consumidor.component.html',
  styleUrl: './edit-consumidor.component.css'
})
export class EditConsumidorComponent {



  public usuario : Usuario = <Usuario>{};
  public email : string = "";

  constructor(
    private authService: AuthService,
    private usuarioService: UsuarioService,
    private ar: ActivatedRoute
  ) {
    this.usuarioService.getUsuarioByUsername(this.authService.getDecodedUsername()).subscribe({
      next : data => {
        this.usuario = data;
        //CAMBIAR ESTO EN LA API
        this.usuario.password = "";
        this.email = data.consumidor.email;
      }
    });
  }

  mandarCreds(info: NgForm) {

  }
}
