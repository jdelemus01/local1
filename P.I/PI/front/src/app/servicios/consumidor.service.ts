import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consumidor } from '../modelos/consumidor';

@Injectable({
  providedIn: 'root'
})
export class ConsumidorService {

  private URL = "http://localhost:8084/consumidor"; 

  constructor( private http: HttpClient) { }

  getConsumidorById(id: number){
    let peticion = `${this.URL}/getCons/`+id;
    console.log(peticion);

    return this.http.get<any>(peticion);
  }
  
  getConsumidorByUsername(usuario: number){
    let peticion = `${this.URL}/getConsUser/`+usuario;
    console.log(peticion);

    return this.http.get<any>(peticion);
  }

  insertConsumidor(consumidor : Consumidor){
    let peticion = `${this.URL}/insCons`;
    console.log(peticion, "Consumidor recibido: ", consumidor);

    return this.http.post<any>(peticion, consumidor);
  }

}
