import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
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
    private authService : AuthService,
    private router : Router
  ){
    //En caso de que se entre al login, se cierra sesión automáticamente
    sessionStorage.removeItem("token");
  }
  //Métodos-----------------------------------------------------------------------------------------------
  mandarCreds(login : Credenciales) {
    this.authService.enviarLogin(login).subscribe({
      next: data => {sessionStorage.setItem("token", data.token);
                        this.router.navigate(["/main/"]);
      } 

    }
    ); 
  }

}
