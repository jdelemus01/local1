import { Routes } from '@angular/router';
import { ListaActividadesComponent } from './componentes/admin-actividades/lista-actividades.component';
import { ListaUsuariosComponent } from './componentes/admin-usuarios/lista-usuarios.component';
import { LoginComponent } from './componentes/login/login.component';
import { ActividadesOferComponent } from './componentes/actividades-ofer/actividades-ofer.component';
import { ActividadFormComponent } from './componentes/actividad-form/actividad-form.component';
import { ActividadesConsComponent } from './componentes/actividades-cons/actividades-cons.component';
import { RegistroComponent } from './componentes/registro/registro.component';
import { MenuComponent } from './componentes/menu/menu.component';
import { MainConsComponent } from './componentes/main-cons/main-cons.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registro',
    component: RegistroComponent,
  },
  {
    path: 'main',
    component: MenuComponent
  },
  {
    path: 'adminActs',
    component: ListaActividadesComponent,
  },
  {
    path: 'adminUsers',
    component: ListaUsuariosComponent,
  },
  {
    path: 'actsByOfer/:id',
    component: ActividadesOferComponent,
  },
  {
    path: 'actForm/:id/:idO',
    component: ActividadFormComponent,
  },
  {
    path: 'mainC/:id',
    component: MainConsComponent,
  },
  {
    path: '**',
    redirectTo: 'login',
  },
];
