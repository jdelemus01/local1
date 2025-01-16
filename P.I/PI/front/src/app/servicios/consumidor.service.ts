import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consumidor } from '../modelos/consumidor';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ConsumidorService {

  private URL = "http://localhost:8084/consumidor";
  private headerToken: HttpHeaders;
  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerToken = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  getConsumidorById(id: number) {
    let peticion = `${this.URL}/getCons/` + id;
    console.log(peticion);
    let headers = this.headerToken;
    return this.http.get<any>(peticion, {headers});
  }

  getConsumidorByUsername(usuario: number) {
    let peticion = `${this.URL}/getConsUser/` + usuario;
    console.log(peticion);

    return this.http.get<any>(peticion);
  }

  insertConsumidor(consumidor: Consumidor) {
    let peticion = `${this.URL}/insCons`;
    console.log(peticion, "Consumidor recibido: ", consumidor);

    return this.http.post<any>(peticion, consumidor);
  }

}
