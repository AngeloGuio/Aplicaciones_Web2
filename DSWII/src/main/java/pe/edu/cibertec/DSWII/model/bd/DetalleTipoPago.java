package pe.edu.cibertec.DSWII.model.bd;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pe.edu.cibertec.DSWII.model.bd.pk.ProductoVentaProductoId;

@Getter
@Setter
@Entity
@Table(name = "detalle_tipopago")
public class DetalleTipoPago {

    @EmbeddedId
    private ProductoVentaProductoId id;
    @Column(name = "numerocuenta")
    private String numerocuenta;
    @Column(name="fechavencimiento", nullable = false, updatable = false)
    @CreationTimestamp
    private String fechavencimiento;

    @ManyToOne
    @MapsId("productid")
    @JoinColumn(name = "productid")
    @JsonBackReference
    private Producto product;

    @ManyToOne
    @MapsId("codventapro")
    @JoinColumn(name = "codventapro")
    @JsonBackReference
    private VentaProducto ventaproducto;

}
