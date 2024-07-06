package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ProductoService implements IProductoService{

    private ProductoRepository productoRepository;
    @Override
    public List<Producto> productoList() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarProductoXID(Integer id) {
        Optional<Producto> productoOptional=productoRepository.findById(id);
        if (productoOptional.isEmpty()){return  Optional.empty();}
        return productoOptional;
    }

    @Override
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto findProductoById(Integer id) {
        return null;
    }



    public boolean registraryActualizarProducto(ProductoDto productoDto){
        try {
            Producto nuevoproducto = new Producto();

            if (productoDto.getIdproducto() != null && productoDto.getIdproducto() > 0) {
                nuevoproducto.setIdproducto(productoDto.getIdproducto());

            }
            TipoProducto tipoProducto = new TipoProducto();
            tipoProducto.setIdtipopro(productoDto.getIdtipopro());
            nuevoproducto.setTipoproducto(tipoProducto);

            Proveedor proveedor = new Proveedor();
            proveedor.setIdproveedor(productoDto.getIdproveedor());
            nuevoproducto.setProveedor(proveedor);

            nuevoproducto.setNombre(productoDto.getNombre());
            nuevoproducto.setCantidad(productoDto.getCantidad());
            nuevoproducto.setPreciopublico(productoDto.getPreciopublico());
            nuevoproducto.setStockminimo(productoDto.getStockminimo());
            nuevoproducto.setStockmaximo(productoDto.getStockmaximo());

            Estado estado = new Estado();
            estado.setCodestado(productoDto.isCodestado());
            nuevoproducto.setEstado(estado);

            Animal animal = new Animal();
            animal.setIdanimal(productoDto.getIdanimal());
            nuevoproducto.setAnimal(animal);

            nuevoproducto.setPrecioproveedor(productoDto.getPrecioproveedor());

            productoRepository.save(nuevoproducto);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
