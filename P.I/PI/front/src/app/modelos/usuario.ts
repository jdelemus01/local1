import { Consumidor } from "./consumidor";
import { Ofertante } from "./ofertante";
import { Rol } from "./rol";

export interface Usuario {
    id: number,
    username: string, 
    email: string,
    consumidor?: Consumidor,
    ofertante?: Ofertante
}
