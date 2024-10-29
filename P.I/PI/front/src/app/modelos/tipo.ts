import { Actividad } from "./actividad";

export interface Tipo {
    id: number,
    nombre: string,
    actividades: Actividad[]
}
