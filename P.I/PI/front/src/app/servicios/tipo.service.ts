import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tipo } from '../modelos/tipo';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  private URL: string = "http://localhost:8084/tipo";
  private headerToken: HttpHeaders;
  
  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerToken = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  getAllTipos() {
    let peticion: string = `${this.URL}/allTipos`;
    console.log(peticion);

    return this.http.get<any[]>(peticion);
  }

  getTipoById(id: number) {
    let peticion = `${this.URL}/getTipo/` + id;
    console.log(peticion);

    return this.http.get<Tipo>(peticion);
  }
}
