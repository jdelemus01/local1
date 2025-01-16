import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Actividad } from '../modelos/actividad';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ActividadService {

  private URL : string = "http://localhost:8084/actividad";
  private headerToken : HttpHeaders;

  constructor(private http:HttpClient, private authService : AuthService) {
    this.headerToken = new HttpHeaders({
      'Content-Type': 'application/json', 
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
   }

  getAllActividades(){
    let peticion = `${this.URL}/allActividades`;
    console.log(peticion);
    let headers = this.headerToken;

    return this.http.get<any[]>(peticion, {headers});
  }

  getActividadesByOfertante(idOf: number){
    let peticion = `${this.URL}/getActsByOf/`+idOf;
    console.log(peticion);

    return this.http.get<any>(peticion);
  }

  deleteActividad(id:number){
    let peticion: string = `${this.URL}/borrarActividad/`+id;
    console.log(peticion);

    return this.http.delete<any>(peticion);
  }

  getActividad(id: number){
    let peticion = `${this.URL}/getActividad/`+id;
    console.log(peticion);

    return this.http.get<any>(peticion);
  }

  insertActividad(actIns: Actividad){
    let peticion = `${this.URL}/insertarActividad`;
    console.log(peticion, actIns);

    return this.http.post<any>(peticion, actIns);
  }

  updateActividad(actUpdate: Actividad){
    let peticion = `${this.URL}/modificarActividad/`+actUpdate.id;
    console.log("El método UPDATE ACTIVIDAD RECIBE: " , peticion, actUpdate);

    return this.http.put<any[]>(peticion, actUpdate);
  }
}
