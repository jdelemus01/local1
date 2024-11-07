import { Routes } from '@angular/router';
import { ListaActividadesComponent } from './componentes/admin-actividades/lista-actividades.component';
import { ListaUsuariosComponent } from './componentes/admin-usuarios/lista-usuarios.component';
import { LoginComponent } from './componentes/login/login.component';
import { UsuarioFormComponent } from './componentes/usuario-form/usuario-form.component';
import { ActividadesOferComponent } from './componentes/actividades-ofer/actividades-ofer.component';
import { ActividadFormComponent } from './componentes/actividad-form/actividad-form.component';
import { ActividadesConsComponent } from './componentes/actividades-cons/actividades-cons.component';

export const routes: Routes = 
[
    {
        path:"login",
        component:LoginComponent
    },
    {
    path:"usForm/:id",
    component:UsuarioFormComponent
    },
    {
        path:"adminActs",
        component:ListaActividadesComponent
    },
    {
        path:"adminUsers",
        component:ListaUsuariosComponent
    },
    {
        path:"actsByOfer/:id",
        component:ActividadesOferComponent
    },
    {
        path:"actForm/:id/:idO",
        component:ActividadFormComponent
    },
    {
        path:"actsCons/:id",
        component:ActividadesConsComponent
    },
    {
      path: "**",
      redirectTo: "login"
    }
];
