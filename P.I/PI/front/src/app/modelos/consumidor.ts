import { Usuario } from "./usuario";

export interface Consumidor {
    id:number,
    nombre: string,
    apellido: string,
    fechaNacimiento: Date,
    email: string, 
    usuario: Usuario
}
