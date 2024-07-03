import { Component } from '@angular/core';
import { CarritoService } from './carrito.service';
import { Producto } from '../home/producto';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [MaterialModule, CommonModule],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css'
})
export class CarritoComponent {
  columnasMostradas: string[] = ['imagen', 'nombre', 'precio', 'cantidad', 'acciones'];
  productos: Producto[];

  constructor(
    private carritoService: CarritoService,
    private router: Router,
    private dialog: MatDialog,
  ) {
    this.productos = this.carritoService.obtenerProductos();
  }

  eliminarProducto(producto: Producto): void {
    this.carritoService.eliminarProducto(producto);
    this.productos = this.carritoService.obtenerProductos();
  }

  vaciarCarrito(): void {
    this.carritoService.vaciarCarrito();
    this.productos = [];
  }

  irDetalleCarrito(): void {
    this.router.navigate(['/detalle-carrito']);
    this.dialog.closeAll();
  }
}