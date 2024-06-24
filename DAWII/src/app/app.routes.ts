import { Routes } from '@angular/router';
import { authGuard } from './auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductosComponent } from './dashboard/productos/productos.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { ContactoComponent } from './dashboard/contacto/contacto.component';
import { NosotrosComponent } from './dashboard/nosotros/nosotros.component';
import { UsuarioComponent } from './dashboard/usuario/usuario.component';

export const routes: Routes = [
    { path: '', 
        component: DashboardComponent, 
        children: [
            { path: '', component: ProductosComponent },
            { path: 'contacto', component: ContactoComponent },
            { path: 'nosotros', component: NosotrosComponent },
            // Rutas protegidas
            { path: 'usuario', component: UsuarioComponent, canActivate: [authGuard] },
        ]
    },
    { path: 'login', component: LoginComponent, canActivate: [authGuard] },
    { path: "**", component: PageNotFoundComponent }
];
