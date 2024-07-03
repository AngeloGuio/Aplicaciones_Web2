package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.VentaProducto;


import java.util.List;
import java.util.Optional;

public interface IVentaProductoService {
    List<VentaProducto> ventaproductoList();
    Optional<VentaProducto> buscarVentaXID(Integer id);
    VentaProducto agregarVenta(VentaProducto ventaproducto);
}
