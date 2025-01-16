import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConsumoActividad } from '../modelos/consumo-actividad';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ConsumoActConsService {

  private URL: string = "http://localhost:8084/consumoActividad";
  private headerToken: HttpHeaders;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerToken = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  getConsumoByConsumidor(id: number) {
    let peticion = `${this.URL}/getConsumoByCons/` + id;
    console.log(peticion);
    let headers = this.headerToken;

    return this.http.get<[any]>(peticion, {headers});
  }

  getConsumoByActividad(id: number) {
    let peticion = `${this.URL}/getConsumoByActs/` + id;
    console.log(peticion);

    return this.http.get<[any]>(peticion);
  }

  insertConsumo(cons: ConsumoActividad) {
    let peticion = `${this.URL}/insertConsumo`;
    console.log(peticion, cons);

    return this.http.post<ConsumoActividad[]>(peticion, cons);
  }

  //Este peta fijo
  deleteConsumo(actId: number, consId: number) {
    let peticion = `${this.URL}/deleteConsumo/` + actId + `/` + consId;
    console.log(peticion);

    return this.http.delete<any>(peticion);
  }
}
