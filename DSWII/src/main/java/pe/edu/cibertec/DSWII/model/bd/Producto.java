package pe.edu.cibertec.DSWII.model.bd;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproducto;
    @ManyToOne
    @JoinColumn(name = "idtipopro")
    private Integer idtipopro;
    @ManyToOne
    @JoinColumn(name = "idproveedor")
    private Integer idproveedor;
    private String nombre;
    private Integer cantidad;
    private Double preciopublico;
    private Integer stockminimo;
    private Integer stockmaximo;
    @ManyToOne
    @JoinColumn(name = "codestado")
    private Integer codestado;
    @ManyToOne
    @JoinColumn(name = "idanimal")
    private Integer idanimal;
    private Double precioproveedor;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<DetalleTipoPago> ventaproductos = new HashSet<>();
}
