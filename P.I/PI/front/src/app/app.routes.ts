import { Routes } from '@angular/router';
import { HomeComponent } from './componentes/home/home.component';
import { ListaActividadesComponent } from './componentes/lista-actividades/lista-actividades.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { LoginComponent } from './componentes/login/login.component';
import { ActividadFormComponent } from './componentes/actividad-form/actividad-form.component';
import { UsuarioFormComponent } from './componentes/usuario-form/usuario-form.component';
import { ActividadComponent } from './componentes/actividad/actividad.component';
import { UsuarioComponent } from './componentes/usuario/usuario.component';

export const routes: Routes = 
[
    {
        path:"",
        component:HomeComponent
    },
    {
        path:"actividades",
        component:ListaActividadesComponent
    },
    {
        path:"usuarios",
        component:ListaUsuariosComponent
    },
    {
        path:"login",
        component:LoginComponent
    },
    {
        path:"actividadForm/:id",
        component:ActividadFormComponent
    },
    {
        path:"actividad/:id",
        component:ActividadComponent
    },
    {
        path:"usuarioForm/:id",
        component:UsuarioFormComponent
    },
    {
        path:"usuario/:id",
        component:UsuarioComponent
    }
];
