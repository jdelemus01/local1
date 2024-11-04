import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  private URL: string = "http://localhost:8084/tipo";
  
  constructor(private http: HttpClient) { }

  getAllTipos(){
    let peticion:string = `${this.URL}/allTipos`;
    console.log(peticion);

    return this.http.get<any[]>(peticion);
  }
}
