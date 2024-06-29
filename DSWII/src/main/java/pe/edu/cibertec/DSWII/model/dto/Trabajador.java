package pe.edu.cibertec.DSWII.model.dto;

import lombok.Data;

@Data
public class Trabajador {
    private Integer idtrabajador;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private String direccion;
    private Integer idcargo;
    private Integer idtipoarea;
    private String password;
}
