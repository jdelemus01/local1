import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credenciales } from '../modelos/credenciales';
import { Jwt } from '../modelos/jwt';
import { Ofertante } from '../modelos/ofertante';
import { Consumidor } from '../modelos/consumidor';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private URL: string = "http://localhost:8084/auth";

  constructor(private http: HttpClient) { }

  enviarLogin(creds : Credenciales){
    let peticion = `${this.URL}/login`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log("EL LOGIN ENVÍA", peticion, creds, headers);

    return this.http.post<Jwt>(peticion, creds, {headers});
  }

  registrarOfertante(ofertante : Ofertante){
    let peticion = `${this.URL}/registroOf`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log("EL REGISTRO ENVÍA", peticion, ofertante, {headers});

    return this.http.post<Ofertante>(peticion, ofertante, {headers});
  }

  registrarConsumidor(consumidor : Consumidor){
    let peticion = `${this.URL}/registroCons`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    console.log("EL REGISTRO ENVÍA", peticion, consumidor, headers);

    return this.http.post<Consumidor>(peticion, consumidor, {headers});
  }
}
