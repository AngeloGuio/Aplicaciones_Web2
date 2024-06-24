package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Producto;
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
}
