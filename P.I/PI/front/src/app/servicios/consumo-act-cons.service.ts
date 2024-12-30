import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConsumoActividad } from '../modelos/consumo-actividad';

@Injectable({
  providedIn: 'root'
})
export class ConsumoActConsService {

  private URL : string = "http://localhost:8084/consumoActividad";

  constructor(private http:HttpClient) { }

  getConsumoByConsumidor(id:number){
    let peticion = `${this.URL}/getConsumoByCons/`+id;
    console.log(peticion);

    return this.http.get<[any]>(peticion);
  }

  getConsumoByActividad(id:number){
    let peticion = `${this.URL}/getConsumoByActs/`+id;
    console.log(peticion);

    return this.http.get<[any]>(peticion);
  }

  insertConsumo(cons : ConsumoActividad){
    let peticion = `${this.URL}/insertConsumo`;
    console.log(peticion, cons);

    return this.http.post<ConsumoActividad[]>(peticion, cons);
  }

  //Este peta fijo
  deleteConsumo(actId : number, consId : number){
    let peticion = `${this.URL}/deleteConsumo/`+actId+`/`+consId;
    console.log(peticion);

    return this.http.delete<any>(peticion);
  }
}
