import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credenciales } from '../modelos/credenciales';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private URL: string = "http://localhost:8084/auth";

  constructor(private http: HttpClient) { }

  enviarLogin(creds : Credenciales){
    let peticion = `${this.URL}/login`;
    console.log("EL LOGIN ENVÍA", peticion, creds);

    return this.http.post<any>(peticion, creds);
  }


}
