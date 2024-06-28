package pe.edu.cibertec.DSWII.model.bd;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="venta_producto")
public class VentaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codventapro;

    @ManyToOne
    @JoinColumn(name = "idproducto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idtrabajador")
    private Trabajador idtrabajador;

    @Column(name="cantidad")
    private Integer cantidad;

    @Column(name="fecha", nullable = false, updatable = false)
    @CreationTimestamp
    private String fecha;

    @Column(name="direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "idtipopago")
    private TipoPago tipopago;

    @Column(name="montototal")
    private double montototal;
    @OneToMany(mappedBy = "ventaproducto",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<DetalleTipoPago> products = new HashSet<>();
}
