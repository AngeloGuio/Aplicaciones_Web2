import { Injectable } from '@angular/core';
import { Producto } from '../productos/producto';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private carrito: Producto[] = [];
  private carritoCantidad = new BehaviorSubject<number>(0);

  constructor() {
    this.cargarCarrito();
  }


  obtenerCantidadProductos(): BehaviorSubject<number> {
    return this.carritoCantidad;
  }

  obtenerProductos(): Producto[] {
    return this.carrito;
  }

  agregarProducto(producto: Producto): void {
    // Verifica si el producto ya está en el carrito
    const productoExistente = this.carrito.find(p => p.idproducto === producto.idproducto);
    if (productoExistente) {
      // Incrementa la cantidad si el producto ya existe
      productoExistente.cantidad += 1;
    } else {
      // Agrega el producto al carrito con cantidad inicial de 1
      this.carrito.push({ ...producto, cantidad: 1 });
    }
    this.guardarCarrito();
    this.actualizarCantidadProductos();
  }

  eliminarProducto(producto: Producto): void {
    // Implementa la lógica para eliminar un producto del carrito
    this.carrito = this.carrito.filter(p => p.idproducto !== producto.idproducto);
    this.guardarCarrito();
    this.actualizarCantidadProductos();
  }

  vaciarCarrito(): void {
    // Implementa la lógica para vaciar el carrito
    this.carrito = [];
    this.guardarCarrito();
    this.actualizarCantidadProductos();
  }

  private cargarCarrito(): void {
    // Carga el carrito desde localStorage
    const carritoGuardado = localStorage.getItem('carrito');
    this.carrito = carritoGuardado ? JSON.parse(carritoGuardado) : [];
    this.actualizarCantidadProductos();
  }

  private guardarCarrito(): void {
    // Guarda el carrito en localStorage
    localStorage.setItem('carrito', JSON.stringify(this.carrito));
  }

  private actualizarCantidadProductos(): void {
    // La cantidad ahora reflejará el número de tipos de productos diferentes en el carrito
    const cantidad = this.carrito.length;
    this.carritoCantidad.next(cantidad);
  }
  
}
