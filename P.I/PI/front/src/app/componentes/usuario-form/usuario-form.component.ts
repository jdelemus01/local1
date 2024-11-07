import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router, ActivatedRoute } from '@angular/router';
import { Consumidor } from '../../modelos/consumidor';
import { Usuario } from '../../modelos/usuario';
import { Ofertante } from '../../modelos/ofertante';
import { OfertanteService } from '../../servicios/ofertante.service';
import { ConsumidorService } from '../../servicios/consumidor.service';
import { UsuarioService } from '../../servicios/usuario.service';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.css',
})
export class UsuarioFormComponent {
  //Variables-----------------------------------------------------------------------------------------------
  public mostrarFormCons: boolean = false;
  public mostrarFormOfer: boolean = false;
  public usuarioCons : Usuario = <Usuario>{};
  public consumidor : Consumidor = <Consumidor>{};
  public usuarioOfer : Usuario = <Usuario>{};
  public ofertante : Ofertante = <Ofertante>{};
  public botonAutorellenoCons: boolean = false;
  public botonAutorellenoOfer: boolean = false;
  public usernameCons : string = "";
  public usernameOfer : string = "";

  //Constructor-----------------------------------------------------------------------------------------------
  constructor(
    private ar: ActivatedRoute, 
    private ruta: Router,
    private ofertanteService: OfertanteService,
    private consumidorService: ConsumidorService,
    private usuarioService: UsuarioService
  ) {
    this.consumidor.id = -1;
    this.ofertante.id = -1;
    this.usuarioCons.id = -1;
    this.usuarioOfer.id = -1;

  }

  //Métodos-----------------------------------------------------------------------------------------------
  mostrarFormConsumidor() {
    this.mostrarFormCons = !this.mostrarFormCons;
    if(this.mostrarFormOfer){
      this.mostrarFormOfer = !this.mostrarFormOfer;
    }
  }

  mostrarFormOfertante() {
    this.mostrarFormOfer = !this.mostrarFormOfer;
    if(this.mostrarFormCons){
      this.mostrarFormCons = !this.mostrarFormCons;
    }
  }

  mostrarDatosUsuarioCons(){
    this.botonAutorellenoCons = !this.botonAutorellenoCons;

  }

  mostrarDatosUsuarioOfer(){
    this.botonAutorellenoOfer = !this.botonAutorellenoOfer;
  }

  autoRellenoCons(username: { usernameBuscar: string }){
    this.usuarioService.getUsuarioByUsername(username.usernameBuscar).subscribe(
      dataUs => {
        console.log('Usuario', dataUs);
        this.ofertanteService.getOfertanteByUsername(dataUs.id).subscribe(
          data =>{
            this.usuarioCons;
            this.consumidor = data;
            this.consumidor.id = -1;
            this.consumidor.password = "";
          }
        )
        this.usuarioCons = dataUs;
      }
    )
  }

  autoRellenoOfer(username: { usernameBuscar: string }){
    this.usuarioService.getUsuarioByUsername(username.usernameBuscar).subscribe(
      dataUs => {
        console.log('Usuario', dataUs);
        this.consumidorService.getConsumidorByUsername(dataUs.id).subscribe(
          data =>{
            this.usuarioOfer;
            this.ofertante = data;
            this.ofertante.id = -1;
            this.ofertante.password = "";
            console.log(this.ofertante);
          }
        );
        this.usuarioOfer = dataUs;
      }
    )
  }

  registrarConsumidor() {
    console.log(this.consumidor, this.usuarioCons);
    this.consumidor.usuario = this.usuarioCons;

    this.consumidorService.insertConsumidor(this.consumidor).subscribe(
      data => {
        console.log(data);
      }
    )
    this.ruta.navigate(['']);
  }

  registrarOfertante() {
    console.log(this.ofertante, this.usuarioOfer);
    
    this.ofertante.usuario = this.usuarioOfer;
    if(this.ofertante.password == "" || this.ofertante.password == null){
      alert;
    }

    this.ofertanteService.insertOfertante(this.ofertante).subscribe(
      data => {
        console.log(data);
      }
    )
    
    this.ruta.navigate(['']);
  }
}
