package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.VentaProducto;
import pe.edu.cibertec.DSWII.repository.VentaProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VentaProductoService implements IVentaProductoService {

    private VentaProductoRepository ventaProductoRepository;

    @Override
    public List<VentaProducto> ventaproductoList() {
        return ventaProductoRepository.findAll();
    }

    @Override
    public Optional<VentaProducto> buscarVentaXID(Integer id) {
        Optional<VentaProducto> ventaProductoOptional=ventaProductoRepository.findById(id);
        if (ventaProductoOptional.isEmpty()){return  Optional.empty();}
        return ventaProductoOptional;
    }

    @Override
    public VentaProducto agregarVenta(VentaProducto ventaproducto) {
        return ventaProductoRepository.save(ventaproducto);
    }
}
