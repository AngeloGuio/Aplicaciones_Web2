package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducto;
    private Integer idtipopro;
    private Integer idproveedor;
    private String nombre;
    private Integer cantidad;
    private Double preciopublico;
    private Integer stockminimo;
    private Integer stockmaximo;
    private Integer codestado;
    private Integer idanimal;
    private Double precioproveedor;
}
