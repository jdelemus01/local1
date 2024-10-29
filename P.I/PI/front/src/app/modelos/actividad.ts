import { Ofertante } from "./ofertante";
import { Tipo } from "./tipo";

export interface Actividad {
    id: number,
    nombre: string,
    duracion: number,
    precio: number,
    airLibre: boolean,
    mas18: boolean,
    ofertante: Ofertante,
    tipo: Tipo
}
