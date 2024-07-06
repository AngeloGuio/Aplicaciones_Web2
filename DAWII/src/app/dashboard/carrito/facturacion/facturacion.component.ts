import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { CarritoService } from '../carrito.service';
import { Router } from '@angular/router';
import { Producto } from '../../home/producto';
import { TipoPago } from './TipoPago';
import { TipopagoService } from './tipopago.service';
import { FacturacionService } from './facturacion.service';
import { RegistroVentaDto } from './RegistroVentaDto';
import { DetalleVentaDto } from './DetalleVenta';
import Notiflix from 'notiflix';

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
    nombres: '',
    apellidos: '',
    telefono: '',
    direccion: '',
    correo: '',
    dni: ''
  };

  datosPago = {
    numerotarjeta: '',
    fechavencimiento: '',
    cvv: 0
  };

  tipoPagoSeleccionado = 0;


  constructor(
    private carritoService: CarritoService,
    private router: Router,
    private tipoPagoService: TipopagoService,
    private facturacionService: FacturacionService
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
    // Preparar los detalles de venta basados en los productos del carrito
    const detallesVenta: DetalleVentaDto[] = this.productos.map(producto => ({
      idproducto: producto.idproducto,
      cantidad: producto.cantidad,
      precio: producto.preciopublico
    }));
  
    const fechaVencimientoISO = new Date(this.datosPago.fechavencimiento);
    // Eliminar la 'Z' y lo que sigue después para coincidir con el formato esperado por el backend
    const fechaVencimientoSinZonaHoraria = fechaVencimientoISO.toISOString().split('.')[0];

    // Crear el objeto RegistroVentaDto
    const registroVentaDto: RegistroVentaDto = {
      ventaDto: {
        fecha: new Date().toISOString().split('.')[0],
        direccion: this.datosPersonales.direccion,
        idtipopago: this.tipoPagoSeleccionado,
        montototal: this.total,
        detallesVenta: detallesVenta
      },
      clienteDto: this.datosPersonales,
      detallePagoDto: {
        numerotarjeta: this.datosPago.numerotarjeta,
        fechavencimiento: fechaVencimientoSinZonaHoraria,
        cvv: this.datosPago.cvv
      }
    };
  
    // Enviar los datos al backend
    this.facturacionService.registrarVenta(registroVentaDto).subscribe({
      next: (response) => {
        // Luego de confirmar el pedido, vacía el carrito y redirige al usuario a la página de confirmación
        if (response.success) {
          this.carritoService.vaciarCarrito();
          this.router.navigate(['/']);
          Notiflix.Notify.success(response.message)
        }
      },
      error: (error) => {
        console.error('Error al registrar la venta:', error);
      }
    });
  }
  

  regresar(): void{
    this.router.navigate(['/detalle-carrito']);
  }
}