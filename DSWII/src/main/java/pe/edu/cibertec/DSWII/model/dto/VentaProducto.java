package pe.edu.cibertec.DSWII.model.dto;

import lombok.Data;

@Data
public class VentaProducto {
    private Integer codventapro;
    private Integer idtrabajador;
    private Integer idusuario;
    private Integer cantidad;
    private String fecha;
    private String direccion;
    private Integer idtipopago;
    private double montototal;
}
