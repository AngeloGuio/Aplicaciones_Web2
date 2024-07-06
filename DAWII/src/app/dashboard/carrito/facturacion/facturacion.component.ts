import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { CarritoService } from '../carrito.service';
import { Router } from '@angular/router';
import { Producto } from '../../home/producto';
import { TipoPago } from './TipoPago';
import { TipopagoService } from './tipopago.service';

@Component({
  selector: 'app-facturacion',
  standalone: true,
  imports: [MaterialModule],
  templateUrl: './facturacion.component.html',
  styleUrl: './facturacion.component.css'
})
export class FacturacionComponent implements OnInit {
  tiposPago: TipoPago[] = [];
  productos: Producto[] = [];
  columnasMostradas: string[] = ['nombre', 'precio', 'cantidad', 'subtotal'];
  costoTransporte: number = 10.00;

  total: number = 0;
  
  datosPersonales = {
    nombre: '',
    direccion: '',
    email: '',
    telefono: ''
  };

  datosPago = {
    numeroTarjeta: '',
    fechaExpiracion: '',
    cvv: ''
  };

  tipoPagoSeleccionado = {
    
  }

  constructor(
    private carritoService: CarritoService,
    private router: Router,
    private tipoPagoService: TipopagoService,
  ) {}

  ngOnInit(): void {
    this.productos = this.carritoService.obtenerProductos();
    this.calcularTotal();
    this.cargarTiposPago();
  }

  cargarTiposPago(): void {
    this.tipoPagoService.obtenerTiposPago().subscribe(
      (data) => {
        console.log(data)
        this.tiposPago = data;
      },
      (error) => {
        console.error('Error al obtener los tipos de pago:', error);
      }
    );
  }

  calcularSubtotal(producto: Producto): number {
    return producto.cantidad * producto.preciopublico;
  }

  calcularSubtotalSinTransporte(): number {
    return this.productos.reduce((acc, producto) => acc + this.calcularSubtotal(producto), 0);
  }

  calcularTotal(): void {
    const subtotal = this.productos.reduce((acc, producto) => acc + this.calcularSubtotal(producto), 0);
    this.total = subtotal + this.costoTransporte;
  }

  confirmarPedido(): void {
    // Aquí podrías implementar la lógica para enviar los datos del pedido al servidor o procesar el pago.
    console.log('Datos personales:', this.datosPersonales);
    console.log('Datos de pago:', this.datosPago);
    console.log('Productos:', this.productos);
    console.log('Total a pagar:', this.total);

    // Luego de confirmar el pedido, vacía el carrito y redirige al usuario a la página de confirmación
    this.carritoService.vaciarCarrito();
    this.router.navigate(['/confirmacion']);
  }

  regresar(): void{
    this.router.navigate(['/detalle-carrito']);
  }
}