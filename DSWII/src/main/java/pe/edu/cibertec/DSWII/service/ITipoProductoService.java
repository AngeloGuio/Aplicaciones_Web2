package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.TipoProducto;

import java.util.List;
import java.util.Optional;

public interface ITipoProductoService {
    List<TipoProducto>listartipoProducto();
    Optional<TipoProducto> buscarTipoProductoXID(Integer id);
    TipoProducto agregarTipoProducto(TipoProducto tipoProducto);
    TipoProducto findTipoProductoById(Integer id);
    TipoProducto actualizarTipoProducto(Integer id, TipoProducto tipoProductoActualizado);
}
