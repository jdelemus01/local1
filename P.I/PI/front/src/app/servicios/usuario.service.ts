import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../modelos/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private URL: string = "http://localhost:8084/usuario";

  constructor(private http: HttpClient) { }

  getAllUsuarios(){
    let peticion:string=`${this.URL}/allUsuarios`;
    console.log(peticion);
    return this.http.get<any[]>(peticion);
  }
}
