package pe.edu.cibertec.DSWII.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DetallePagoDto {
    private Integer idpago;
    private Integer idventa;
    private String numerotarjeta;
    private String fechavencimiento; // Formato ISO 8601
    private Integer cvv;
}