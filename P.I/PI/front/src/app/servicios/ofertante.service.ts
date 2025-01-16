import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ofertante } from '../modelos/ofertante';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class OfertanteService {

  private URL: string = "http://localhost:8084/ofertante";
  private headerToken: HttpHeaders;
  
  constructor(private http: HttpClient, private authService: AuthService) {
    this.headerToken = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  getAllOfertantes() {
    let peticion = `${this.URL}/allOfertantes`;
    console.log(peticion);

    return this.http.get<any[]>(peticion);
  }

  getOfertanteById(id: number) {
    let peticion = `${this.URL}/getOfertante/` + id;
    console.log(peticion);

    return this.http.get<Ofertante>(peticion);
  }

  getOfertanteByUsername(usuario: number) {
    let peticion = `${this.URL}/getOfertanteUser/` + usuario;
    console.log(peticion);

    return this.http.get<Ofertante>(peticion);
  }

  insertOfertante(ofertante: Ofertante) {
    let peticion = `${this.URL}/insOfer`;
    console.log(peticion, "Ofertante recibido: ", ofertante);

    return this.http.post<any>(peticion, ofertante);
  }
}
