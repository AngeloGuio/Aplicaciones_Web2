import { Routes } from '@angular/router';
import { authGuard } from './auth.guard';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './dashboard/home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { ContactoComponent } from './dashboard/contacto/contacto.component';
import { NosotrosComponent } from './dashboard/nosotros/nosotros.component';
import { UsuarioComponent } from './dashboard/usuario/usuario.component';
import { PagoComponent } from './dashboard/carrito/detalle-carrito/detalle-carrito.component';
import { FacturacionComponent } from './dashboard/carrito/facturacion/facturacion.component';
import { UsuarioDetalleComponent } from './dashboard/usuario/usuario-detalle/usuario-detalle.component';
import { ProductosComponent } from './dashboard/productos/productos.component';
import { ProductoDetalleComponent } from './dashboard/productos/producto-detalle/producto-detalle.component';
import { CategoriaComponent } from './dashboard/categoria/categoria.component';
import { CategoriaDetalleComponent } from './dashboard/categoria/categoria-detalle/categoria-detalle.component';

export const routes: Routes = [
    { path: '', 
        component: DashboardComponent, 
        children: [
            { path: '', component: HomeComponent },
            { path: 'contacto', component: ContactoComponent },
            { path: 'nosotros', component: NosotrosComponent },
            { path: 'pago', component: NosotrosComponent },
            { path: 'detalle-carrito', component: PagoComponent },
            { path: 'facturacion', component: FacturacionComponent},
            // Rutas protegidas
            { path: 'usuario', component: UsuarioComponent, canActivate: [authGuard], data: { protegida: true } },
            // { path: 'usuario', component: UsuarioListaComponent, canActivate: [authGuard] },
            { path: "usuario/:id", component: UsuarioDetalleComponent, canActivate: [authGuard]},
            { path: 'producto', component: ProductosComponent, canActivate: [authGuard] },
            { path: "producto/:id", component: ProductoDetalleComponent, canActivate: [authGuard]},
            { path: 'categoria', component: CategoriaComponent, canActivate: [authGuard] },
            { path: "categoria/:id", component: CategoriaDetalleComponent, canActivate: [authGuard]},
        ]
    },
    { path: 'login', component: LoginComponent, canActivate: [authGuard] },
    { path: "**", component: PageNotFoundComponent }
];
