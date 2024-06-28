package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "trabajador")
public class Trabajador {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtrabajador;
    private String nombres;
    private String apellidos;
    private String dni;
    private String  telefono;
    private String correo;
    private String direccion;
    @ManyToOne
    @JoinColumn(name = "idcargo")
    private Integer idcargo;
    @ManyToOne
    @JoinColumn(name = "idtipoarea")
    private Integer idtipoarea;
}
