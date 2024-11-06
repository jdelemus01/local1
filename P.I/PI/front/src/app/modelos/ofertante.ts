import { Actividad } from "./actividad";
import { Usuario } from "./usuario";

export interface Ofertante {
    id:number,
    nombre: string,
    apellido: string,
    password: string,
    fechaNacimiento: Date
    usuario: Usuario,
    actividades: Actividad[]
}
