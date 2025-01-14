import { Actividad } from "./actividad";
import { Usuario } from "./usuario";

export interface Ofertante {
    id:number,
    email : string,
    usuario: Usuario,
    actividades: Actividad[]
}
