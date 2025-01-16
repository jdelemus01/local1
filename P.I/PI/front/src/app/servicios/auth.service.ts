import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credenciales } from '../modelos/credenciales';
import { Jwt } from '../modelos/jwt';
import { Ofertante } from '../modelos/ofertante';
import { Consumidor } from '../modelos/consumidor';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

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

  saveToken(jwt : string){
    sessionStorage.setItem("token", jwt)
  }

  getToken(){
    return sessionStorage.getItem("token");
  }

  decodeToken(token : string){
    const util = token.split(".")[1];

    return JSON.parse(atob(util));
  }

  getDecodedUsername(){
    let token = this.getToken();
    let userName = "";

    if(token != "" && token != null){
        userName = this.decodeToken(token).sub;
    }

    return userName;
  }

  getOfeRoleDecoded(){
    let token = this.getToken();
    let ofeRole = "";

    if(token != "" && token != null){
        ofeRole = this.decodeToken(token).ofe;
    }

    return ofeRole;
  }

  getConsRoleDecoded(){
    let token = this.getToken();
    let consRole = "";

    if(token != "" && token != null){
        consRole = this.decodeToken(token).cons;
    }

    return consRole;
  }
}
