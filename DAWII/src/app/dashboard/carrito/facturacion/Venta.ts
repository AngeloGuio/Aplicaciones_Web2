import { DetalleVentaDto } from "./DetalleVenta";

export interface VentaDto {
    fecha: string;
    direccion: string;
    idtipopago: number;
    montototal: number;
    detallesVenta: DetalleVentaDto[];
}
  