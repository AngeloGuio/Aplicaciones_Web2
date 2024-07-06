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
import java.util.stream.Collectors;

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

        Cliente cliente = new Cliente();
        cliente.setIdcliente(ventaProductoDto.getIdcliente());

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

    public VentaProductoDto convertirADto(VentaProducto ventaProducto) {
        VentaProductoDto ventaProductoDto = new VentaProductoDto();
        ventaProductoDto.setCodventapro(ventaProducto.getCodventapro());
        ventaProductoDto.setIdcliente(ventaProducto.getCliente() != null ? ventaProducto.getCliente().getIdcliente() : null);
        ventaProductoDto.setCantidad(ventaProducto.getCantidad());
        ventaProductoDto.setFecha(ventaProducto.getFecha().toString());
        ventaProductoDto.setDireccion(ventaProducto.getDireccion());
        ventaProductoDto.setIdtipopago(ventaProducto.getTipopago() != null ? ventaProducto.getTipopago().getIdtipopago() : null);
        ventaProductoDto.setMontototal(ventaProducto.getMontototal());

        List<ProductoDto> productos = ventaProducto.getDetalleTipoPagoList()
                .stream()
                .map(detalle -> {
                    ProductoDto productoDto = new ProductoDto();
                    Producto producto = detalle.getProducto();
                    productoDto.setIdproducto(producto.getIdproducto());
                    productoDto.setIdtipopro(producto.getTipoproducto().getIdtipopro());
                    productoDto.setIdproveedor(producto.getProveedor().getIdproveedor());
                    productoDto.setNombre(producto.getNombre());
                    productoDto.setCantidad(producto.getCantidad());
                    productoDto.setPreciopublico(producto.getPreciopublico());
                    productoDto.setStockminimo(producto.getStockminimo());
                    productoDto.setStockmaximo(producto.getStockmaximo());
                    productoDto.setCodestado(producto.getEstado().getCodestado());
                    productoDto.setIdanimal(producto.getAnimal().getIdanimal());
                    productoDto.setPrecioproveedor(producto.getPrecioproveedor());
                    return productoDto;
                })
                .collect(Collectors.toList());
        ventaProductoDto.setProduclist(productos);

        return ventaProductoDto;
    }

}
