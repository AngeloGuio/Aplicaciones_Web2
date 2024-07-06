import { ClienteDto } from "./Cliente";
import { DetallePagoDto } from "./DetallePago";
import { VentaDto } from "./Venta";

export interface RegistroVentaDto {
    ventaDto: VentaDto;
    clienteDto: ClienteDto;
    detallePagoDto: DetallePagoDto;
  }
  