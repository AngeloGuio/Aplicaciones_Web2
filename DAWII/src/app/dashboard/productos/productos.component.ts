// productos.component.ts
import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';
import { ProductoService } from './producto.service';
import { Producto } from './producto';
import { CarritoService } from '../carrito/carrito.service';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [MaterialModule, CommonModule],
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {
  productos: Producto[] = [];

  constructor(
    private productoService: ProductoService,
    private carritoService: CarritoService
  ) {}

  ngOnInit(): void {
    this.productoService.getAllProducts().subscribe((data) => {
      this.productos = data;
    });
  }

  agregarAlCarrito(producto: Producto): void {
    this.carritoService.agregarProducto(producto);
  }
}
