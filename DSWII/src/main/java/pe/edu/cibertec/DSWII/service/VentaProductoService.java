package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.dto.VentaProductoDto;
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
    @Transactional()
    public boolean registrarVentasYDetalleTipoPago(VentaProductoDto ventaProductoDto){
        try {
        VentaProducto venta = new VentaProducto();


        TipoPago tipoPago = new TipoPago();
        tipoPago.setIdtipopago(ventaProductoDto.getIdtipopago());
        venta.setTipopago(tipoPago);
        VentaProducto nuevaVentaProducto = ventaProductoRepository.save(venta);
        DetalleTipoPago detalleTipoPago = new DetalleTipoPago();
        for (ProductoDto productoDto: ventaProductoDto.getProduclist()){
            Producto producto = new Producto();
            producto.setIdproducto(productoDto.getIdproducto());
            detalleTipoPago.setProducto(producto);

        }

        return true;
    } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
