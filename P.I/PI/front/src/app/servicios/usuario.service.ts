import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../modelos/usuario';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private URL: string = "http://localhost:8084/usuario";
  private headerToken: HttpHeaders;
  
  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerToken = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  getAllUsuarios() {
    let peticion: string = `${this.URL}/allUsuarios`;
    console.log(peticion);
    let headers = this.headerToken;

    return this.http.get<any[]>(peticion, {headers});
  }

  getUsuarioById(id: number) {
    let peticion: string = `${this.URL}/getUsuario/` + id;
    console.log(peticion);
    let headers = this.headerToken;

    return this.http.get<any>(peticion, {headers});
  }

  getUsuarioByUsername(username: string) {
    let peticion: string = `${this.URL}/getUsuarioUsername/` + username;
    console.log(peticion);
    let headers = this.headerToken;

    return this.http.get<any>(peticion, {headers});
  }
}
