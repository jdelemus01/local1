import { Consumidor } from "./consumidor";
import { Ofertante } from "./ofertante";

export interface Usuario {
    id: number,
    nombre : string,
    apellidos : string,
    fechaNacimiento: Date,
    username: string, 
    password : string
}
