package pe.edu.cibertec.DSWII.Util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.VentaProducto;
import pe.edu.cibertec.DSWII.model.dto.DtoEntity;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.dto.VentaProductoDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoUtil {

    public DtoEntity convertirADto (Object obj, DtoEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public Object convertirAEntity(Object obj, Class<Producto> mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }

    public ProductoDto convertirADto(Producto producto, ProductoDto productoDto) {
        productoDto.setIdproducto(producto.getIdproducto());
        if (producto.getTipoproducto() != null) {
            productoDto.setIdtipopro(producto.getTipoproducto().getIdtipopro());
        }
        if (producto.getProveedor() != null) {
            productoDto.setIdproveedor(producto.getProveedor().getIdproveedor());
        }
        productoDto.setNombre(producto.getNombre());
        productoDto.setCantidad(producto.getCantidad());
        productoDto.setPreciopublico(producto.getPreciopublico());
        productoDto.setStockminimo(producto.getStockminimo());
        productoDto.setStockmaximo(producto.getStockmaximo());
        if (producto.getEstado() != null) {
            productoDto.setCodestado(producto.getEstado().getCodestado());
        }
        if (producto.getAnimal() != null) {
            productoDto.setIdanimal(producto.getAnimal().getIdanimal());
        }
        productoDto.setPrecioproveedor(producto.getPrecioproveedor());
        return productoDto;
    }



}
