import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tipo } from '../modelos/tipo';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  private URL: string = "http://localhost:8084/tipo";
  
  constructor(private http: HttpClient) { }

  getAllTipos(){
    let peticion:string = `${this.URL}/allTipos`;
    console.log(peticion);

    return this.http.get<any[]>(peticion);
  }

  getTipoById(id: number){
    let peticion = `${this.URL}/getTipo/`+id;
    console.log(peticion);

    return this.http.get<Tipo>(peticion);
  }
}
