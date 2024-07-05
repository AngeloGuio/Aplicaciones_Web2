import { Routes } from '@angular/router';
import { authGuard } from './auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductosComponent } from './dashboard/home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { ContactoComponent } from './dashboard/contacto/contacto.component';
import { NosotrosComponent } from './dashboard/nosotros/nosotros.component';
import { UsuarioComponent } from './dashboard/usuario/usuario.component';
import { PagoComponent } from './dashboard/carrito/detalle-carrito/detalle-carrito.component';
import { FacturacionComponent } from './dashboard/carrito/facturacion/facturacion.component';

export const routes: Routes = [
    { path: '', 
        component: DashboardComponent, 
        children: [
            { path: '', component: ProductosComponent },
            { path: 'contacto', component: ContactoComponent },
            { path: 'nosotros', component: NosotrosComponent },
            { path: 'pago', component: NosotrosComponent },
            { path: 'detalle-carrito', component: PagoComponent },
            { path: 'facturacion', component: FacturacionComponent},
            // Rutas protegidas
            { path: 'usuario', component: UsuarioComponent, canActivate: [authGuard], data: { protegida: true } },
        ]
    },
    { path: 'login', component: LoginComponent, canActivate: [authGuard] },
    { path: "**", component: PageNotFoundComponent }
];
