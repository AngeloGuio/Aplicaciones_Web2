package pe.edu.cibertec.DSWII.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleVentaDto {
    private Integer iddetalle;
    private Integer idventa;
    private Integer idproducto;
    private Integer cantidad;
    private BigDecimal precio;
}
