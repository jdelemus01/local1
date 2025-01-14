import { Usuario } from "./usuario";

export interface Consumidor {
    id:number,
    email : string,
    usuario: Usuario
}
