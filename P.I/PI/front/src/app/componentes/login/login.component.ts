import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Credenciales } from '../../modelos/credenciales';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { OfertanteService } from '../../servicios/ofertante.service';
import { UsuarioService } from '../../servicios/usuario.service';
import { LoginService } from '../../servicios/login.service';

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
    private loginService : LoginService
  ){

  }
  //Métodos-----------------------------------------------------------------------------------------------
  mandarCreds(login : Credenciales) {
    this.loginService.enviarLogin(login).subscribe({
      next: data => {console.log(data);} 

    }
    ); 
  }

}
