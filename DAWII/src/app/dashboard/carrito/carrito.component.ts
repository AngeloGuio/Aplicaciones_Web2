import { Component } from '@angular/core';
import { CarritoService } from './carrito.service';
import { Producto } from '../productos/producto';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [MaterialModule, CommonModule],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css'
})
export class CarritoComponent {
  columnasMostradas: string[] = ['imagen', 'nombre', 'precio', 'cantidad', 'acciones'];
  productos: Producto[]; // Define la interfaz o clase Producto según tus necesidades

  constructor(private carritoService: CarritoService) {
    this.productos = this.carritoService.obtenerProductos(); // Implementa este método en tu servicio
  }

  eliminarProducto(producto: Producto): void {
    this.carritoService.eliminarProducto(producto); // Implementa este método en tu servicio
    // Actualiza la lista de productos después de eliminar
    this.productos = this.carritoService.obtenerProductos();
  }

  vaciarCarrito(): void {
    this.carritoService.vaciarCarrito(); // Implementa este método en tu servicio
    // Actualiza la lista de productos después de vaciar el carrito
    this.productos = [];
  }
}