import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../servicios/auth.service';
import { environment } from '../../../environments/environment';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { OfertanteService } from '../../servicios/ofertante.service';
import { UsuarioService } from '../../servicios/usuario.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  public banner: string;
  public showButtonOfe: boolean = false;
  public showButtonCons: boolean = false;
  public idOf: number = -1;
  public idC: number = -1;

  constructor(
    private authService: AuthService,
    private consumidorService: ConsumidorService,
    private ofertanteService: OfertanteService,
    private usuarioService : UsuarioService,
    private router: Router

  ) {
    this.banner = this.authService.getDecodedUsername();

    this.showButtonOfe = this.authService.getOfeRoleDecoded() == 0;
    
    this.showButtonCons = this.authService.getConsRoleDecoded() == 0;

    if (environment.checkIfTokenExists()) {
      this.router.navigate(["/"]);
    } else {
      this.usuarioService.getUsuarioByUsername(this.authService.getDecodedUsername()).subscribe({
        next : data => {
          if(data.ofertante != null){
            this.idOf = data.ofertante.id;
        }
        if(data.consumidor != null){
          this.idC = data.consumidor.id;
        }
      }
      })
    }
  }
}
