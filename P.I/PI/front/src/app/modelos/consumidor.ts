import { Usuario } from "./usuario";

export interface Consumidor {
    id:number,
    nombre: string,
    apellido: string,
    password: string,
    fechaNacimiento: Date,
    usuario: Usuario
}
