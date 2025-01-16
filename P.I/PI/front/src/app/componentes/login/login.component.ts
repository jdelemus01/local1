import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Credenciales } from '../../modelos/credenciales';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { OfertanteService } from '../../servicios/ofertante.service';
import { UsuarioService } from '../../servicios/usuario.service';
import { AuthService } from '../../servicios/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  //Variables---------------------------------------------------------------------------------------------
  public creds : Credenciales = <Credenciales>{};
  //Constructor-------------------------------------------------------------------------------------------
  constructor(
    /*
    private consService : ConsumidorService, 
    private oferService : OfertanteService,
    private usuarioService : UsuarioService
     */
    private authService : AuthService
  ){

  }
  //Métodos-----------------------------------------------------------------------------------------------
  mandarCreds(login : Credenciales) {
    this.authService.enviarLogin(login).subscribe({
      next: data => {sessionStorage.setItem("token", data.token);} 

    }
    ); 
  }

}
