package pe.edu.cibertec.DSWII.model.dto;

import lombok.*;

@AllArgsConstructor
@Getter
public class UsuarioSeguridadDto {
    private Integer idusuario;
    private String nomusuario;
    private String token;
}
