import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, Router, ActivatedRoute } from '@angular/router';
import { Consumidor } from '../../modelos/consumidor';
import { Usuario } from '../../modelos/usuario';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.css',
})
export class UsuarioFormComponent {
  //Variables-----------------------------------------------------------------------------------------------
  public mostrarCons: boolean = false;
  public mostrarOfer: boolean = false;
  public usuario : Usuario = <Usuario>{};
  public consumidor : Consumidor = <Consumidor>{};

  //Constructor-----------------------------------------------------------------------------------------------
  constructor(
    private ar: ActivatedRoute, 
    private ruta: Router
  ) {
    
  }

  //Métodos-----------------------------------------------------------------------------------------------
  mostrarFormConsumidor() {
    this.mostrarCons = !this.mostrarCons;
  }

  mostrarFormOfertante() {
    this.mostrarOfer = !this.mostrarOfer;
  }

  registrarConsumidor() {
    console.log(this.consumidor, this.usuario);
    this.ruta.navigate(['']);
  }
}
