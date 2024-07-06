package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "detalle_pago")
public class DetallePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpago;
    @OneToOne
    @JoinColumn(name = "idventa")
    private Venta venta;
    private String numerotarjeta;
    private Timestamp fechavencimiento;
    private Integer cvv;
}
