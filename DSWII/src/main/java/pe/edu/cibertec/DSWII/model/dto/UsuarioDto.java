package pe.edu.cibertec.DSWII.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioDto {
    private Integer idusuario;
    private String nomusuario;

    private String token;
}
