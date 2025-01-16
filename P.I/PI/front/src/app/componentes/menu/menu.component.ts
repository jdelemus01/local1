import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../servicios/auth.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  public banner : string;
  public ofeRole;
  public consRole;
  public showButtons : boolean;

  constructor(private authService : AuthService){
    this.banner = authService.getDecodedUsername();
    this.ofeRole = authService.getOfeRoleDecoded();
    this.consRole = authService.getConsRoleDecoded();
    
    if(this.ofeRole == "ofe"  &&  this.consRole == "cons"){
      this.showButtons = true;
    } else {
      this.showButtons = false;
    }
  }

  mostrarMenu(){
    return true;
  }
}
