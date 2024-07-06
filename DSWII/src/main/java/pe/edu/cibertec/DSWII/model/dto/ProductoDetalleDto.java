package pe.edu.cibertec.DSWII.model.dto;

import lombok.Data;

@Data
public class ProductoDetalleDto implements DtoEntity{

    private Integer idproducto;
    private Integer idtipopro;
    private String nombre;
    private Integer idanimal;
}
