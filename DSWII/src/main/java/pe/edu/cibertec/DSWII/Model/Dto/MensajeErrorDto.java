package pe.edu.cibertec.DSWII.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Getter
@Builder
public class MensajeErrorDto {
    private Integer codigoestado;
    private Date fechaerror;
    private String mensaje;
    private String descripcion;
}
