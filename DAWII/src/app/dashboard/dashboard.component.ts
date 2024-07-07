import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { MaterialModule } from '../material/material.module';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { MatBadgeModule } from '@angular/material/badge';
import { CarritoComponent } from './carrito/carrito.component';
import { CarritoService } from './carrito/carrito.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet, MaterialModule, CommonModule, MatBadgeModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})

export class DashboardComponent {
  nombreUsuario: string | null = null;
  cantidadProductosEnCarrito: number = 0;
  mostrarIconoCarrito: boolean = true;

  constructor(
    private router: Router, 
    private route: ActivatedRoute,
    private authService: AuthService,
    public dialog: MatDialog,
    private carritoService: CarritoService
  ){
    this.cargarNombreUsuario();
    this.carritoService.obtenerCantidadProductos().subscribe(cantidad => {
      this.cantidadProductosEnCarrito = cantidad;
    });
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.mostrarIconoCarrito = !event.urlAfterRedirects.includes('/detalle-carrito');
      }
    });
  }

  public estaLogeado(): boolean {
    return this.authService.isLogged();
  }

  public navigateToLogin(): void {
    this.router.navigate(['/login']);
  }

  salir(): void{
    this.authService.logout();
    if (this.route.snapshot.firstChild && this.route.snapshot.firstChild.data['protegida']) {
      this.router.navigate(['/']); // Redirige al usuario a la página de inicio
    }
  }

  cargarNombreUsuario(): void {
    this.nombreUsuario = sessionStorage.getItem('usuario');
  }

  irPortada():void{
    this.router.navigate([""], {relativeTo: this.route})
  }
  irContacto():void{
    this.router.navigate(["contacto"], {relativeTo: this.route})
  }
  irNosotros():void{
    this.router.navigate(["nosotros"], {relativeTo: this.route})
  }
  irModUsuario():void{
    this.router.navigate(["usuario"], {relativeTo: this.route})
  }
  irModProducto():void{
    this.router.navigate(["producto"], {relativeTo: this.route})
  }
  irModCategoria(): void{
    this.router.navigate(["categoria"], {relativeTo: this.route})
  }
  irVenta(): void{
    this.router.navigate(["venta"], {relativeTo: this.route})
  }

  abrirCarrito(): void {
    const dialogRef = this.dialog.open(CarritoComponent, {
      maxHeight: '480px',
      minWidth: '40%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('El diálogo del carrito se cerró');
    });
  }
}
