package pe.edu.cibertec.DSWII.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class VentaProductoDto implements DtoEntity{

    private Integer codventapro;
    private Integer idcliente;
    private String fecha;
    private String direccion;
    private double montototal;
    private Integer idtipopago;
    private Integer cantidad;

    private List<ProductoDetalleDto> produclist;


}
