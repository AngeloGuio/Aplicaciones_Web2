package pe.edu.cibertec.DSWII.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductoDto implements DtoEntity{

    private Integer idproducto;
    private Integer idtipopro;
    private Integer idproveedor;
    private String nombre;
    private Integer cantidad;
    private double preciopublico;
    private Integer stockminimo;
    private Integer stockmaximo;
    private boolean codestado;
    private Integer idanimal;
    private double precioproveedor;

}
