package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> productoList();
    Optional<Producto> buscarProductoXID(Integer id);
    Producto agregarProducto(Producto producto);
}
