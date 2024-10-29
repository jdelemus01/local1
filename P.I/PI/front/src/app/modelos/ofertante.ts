import { Actividad } from "./actividad";
import { Usuario } from "./usuario";

export interface Ofertante {
    id:number,
    nombre: string,
    apellido: string,
    email: string, 
    usuario: Usuario,
    actividades: Actividad[]
}
