import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../../modelos/usuario';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.css'
})
export class UsuarioFormComponent {

  public idUsuario: number;
  public usuario: Usuario = <Usuario>{};
  
  constructor(private ruta: Router, private rutaAct:ActivatedRoute){
    this.idUsuario = this.rutaAct.snapshot.params["id"];
    this.usuario.id = this.idUsuario; 
  }

  modificarUsuario(usuario: Usuario){
    this.ruta.navigate(["usuarios"]);
  }

  volver(){
    this.ruta.navigate(["usuarios"]);
  }
}
