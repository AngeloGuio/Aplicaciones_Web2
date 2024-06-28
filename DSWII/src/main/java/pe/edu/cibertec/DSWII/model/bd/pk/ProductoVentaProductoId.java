package pe.edu.cibertec.DSWII.model.bd.pk;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ProductoVentaProductoId {

    private Integer idproducto;
    private Integer codventapro;

}
