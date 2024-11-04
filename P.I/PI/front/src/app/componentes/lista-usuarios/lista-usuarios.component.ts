import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Usuario } from '../../modelos/usuario';
import { UsuarioService } from '../../servicios/usuario.service';

@Component({
  selector: 'app-lista-usuarios',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './lista-usuarios.component.html',
  styleUrl: './lista-usuarios.component.css'
})
export class ListaUsuariosComponent {

  //Variables-----------------------------------------------------------------------------------------------
  public usuarios : Usuario[] = [];

  //Constructor-----------------------------------------------------------------------------------------------
  constructor(private service:UsuarioService){
    this.service.getAllUsuarios().subscribe(
      data => {
        console.log(data);
        this.usuarios = data;
      }
    )
  }


  //Métodos-----------------------------------------------------------------------------------------------
}
