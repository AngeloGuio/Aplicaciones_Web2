package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddetalle;
    @ManyToOne
    @JoinColumn(name = "idventa")
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "idproducto")
    private Producto producto;
    private Integer cantidad;
    private BigDecimal precio;

}

