package pe.edu.cibertec.DSWII.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VentaDto {
    private Integer idventa;
    private Integer idcliente;
    private String fecha; // Formato ISO 8601
    private String direccion;
    private BigDecimal montototal;
    private Integer idtipopago;
    private List<DetalleVentaDto> detallesVenta;
}
