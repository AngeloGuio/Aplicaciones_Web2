import { Component } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { CommonModule } from '@angular/common';
import { ProductoService } from './producto.service';
import { Producto } from './producto';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [ MaterialModule, CommonModule ],
  templateUrl: './productos.component.html',
  styleUrl: './productos.component.css'
})
export class ProductosComponent {
  productos: Producto[] = [];

  constructor(private productoService : ProductoService ){ }

  ngOnInit(): void {
    this.productoService.getAllProducts()
    .subscribe((data) => {
      this.productos = data;
    })
  }

}
