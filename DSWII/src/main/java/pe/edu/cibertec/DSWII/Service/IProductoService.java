package pe.edu.cibertec.DSWII.Service;

import pe.edu.cibertec.DSWII.Model.Base.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> productoList();
    Optional<Producto> buscarProductoXID(Integer id);
    Producto agregarProducto(Producto producto);
}
