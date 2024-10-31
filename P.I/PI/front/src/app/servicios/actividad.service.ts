import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ActividadService {

  private URL : string = "http://localhost:8084/actividad";

  constructor(private http:HttpClient) { }

  getAllActividades(){
    let peticion = `${this.URL}/allActividades`;
    console.log(peticion);

    return this.http.get<any[]>(peticion);
  }

  deleteActividad(id:number){
    let peticion: string = `${this.URL}/borrarActividad/`+id;
    console.log(peticion);

    return this.http.delete<any>(peticion);
  }
}
