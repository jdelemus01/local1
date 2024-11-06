import { Routes } from '@angular/router';
import { ListaActividadesComponent } from './componentes/admin-actividades/lista-actividades.component';
import { ListaUsuariosComponent } from './componentes/admin-usuarios/lista-usuarios.component';
import { LoginComponent } from './componentes/login/login.component';
import { UsuarioFormComponent } from './componentes/usuario-form/usuario-form.component';
import { ActividadComponent } from './componentes/actividadCrear/actividad.component';

export const routes: Routes = 
[
    {
        path:"adminActs",
        component:ListaActividadesComponent
    },
    {
        path:"adminUsers",
        component:ListaUsuariosComponent
    },
    {
        path:"login",
        component:LoginComponent
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
      path: "**",
      redirectTo: "login"
    }
];
