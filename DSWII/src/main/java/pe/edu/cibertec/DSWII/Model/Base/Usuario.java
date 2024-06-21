package pe.edu.cibertec.DSWII.Model.Base;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;
    private String nomusuario;
    private String password;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;
    private Integer codestado;
}
