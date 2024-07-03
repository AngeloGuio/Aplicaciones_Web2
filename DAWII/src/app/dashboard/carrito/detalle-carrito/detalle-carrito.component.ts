import { Component, OnInit } from '@angular/core';
import { CarritoService } from '../carrito.service';
import { Producto } from '../../home/producto';
import { MaterialModule } from '../../../material/material.module';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-detalle-carrito',
  standalone: true,
  imports: [MaterialModule, CommonModule, FormsModule],
  templateUrl: './detalle-carrito.component.html',
  styleUrl: './detalle-carrito.component.css'
})
export class PagoComponent implements OnInit {
  productos!: Producto[];
  columnasMostradas: string[] = ['imagen', 'nombre', 'precio', 'cantidad', 'subtotal'];

  constructor(
    private carritoService: CarritoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productos = this.carritoService.obtenerProductos();
    if (this.productos.length === 0) {
      // Si no hay productos en el carrito, redirige al portal
      this.router.navigate(['/']);
    }
  }
  
  
  actualizarCantidad(producto: Producto, nuevaCantidad: number): void {
    // Asegúrate de que la nueva cantidad sea un número y no una cadena
    const cantidad = Number(nuevaCantidad);
    if (!isNaN(cantidad) && cantidad > 0) {
      producto.cantidad = cantidad;
      this.carritoService.actualizarProducto(producto);
    }
  }
  

  calcularTotal(): number {
    return this.productos.reduce((acc, producto) => acc + (producto.cantidad * producto.preciopublico), 0);
  }

  procederAlPago(): void {
  }
}