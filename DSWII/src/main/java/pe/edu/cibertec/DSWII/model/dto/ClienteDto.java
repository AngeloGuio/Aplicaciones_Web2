package pe.edu.cibertec.DSWII.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClienteDto {
    private Integer idcliente;
    private String nombres;
    private String apellidos;
    private String correo;
}
