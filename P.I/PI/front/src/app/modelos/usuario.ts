import { Rol } from "./rol";

export interface Usuario {
    id: number,
    username: string, 
    password: string,
    email: string,
    rol: Rol
}
