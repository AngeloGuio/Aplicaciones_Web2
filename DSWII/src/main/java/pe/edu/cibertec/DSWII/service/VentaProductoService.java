package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.ProductoDetalleDto;
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
        VentaProducto nuevaventa = new VentaProducto();

        Cliente cliente = new Cliente();
        cliente.setIdcliente(ventaProductoDto.getIdcliente());

        nuevaventa.setFecha(ventaProductoDto.getFecha());
        nuevaventa.setDireccion(ventaProductoDto.getDireccion());
        nuevaventa.setMontototal(ventaProductoDto.getMontototal());

        TipoPago tipoPago = new TipoPago();
        tipoPago.setIdtipopago(ventaProductoDto.getIdtipopago());

        nuevaventa.setCantidad(ventaProductoDto.getCantidad());


        nuevaventa.setTipopago(tipoPago);
        VentaProducto nuevaVentaProducto = ventaProductoRepository.save(nuevaventa);


        DetalleTipoPago detalleTipoPago = new DetalleTipoPago();
        for (ProductoDetalleDto productoDetalleDto: ventaProductoDto.getProduclist()){
            Producto producto = new Producto();
            producto.setIdproducto(productoDetalleDto.getIdproducto());

            TipoProducto tipoProducto = new TipoProducto();
            tipoProducto.setIdtipopro(productoDetalleDto.getIdtipopro());

            producto.setNombre(productoDetalleDto.getNombre());


            detalleTipoPago.setProductos(producto);

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
        ventaProductoDto.setFecha(ventaProducto.getFecha());
        ventaProductoDto.setDireccion(ventaProducto.getDireccion());
        ventaProductoDto.setMontototal(ventaProducto.getMontototal());
        ventaProductoDto.setIdtipopago(ventaProducto.getTipopago() != null ? ventaProducto.getTipopago().getIdtipopago() : null);
        ventaProductoDto.setCantidad(ventaProducto.getCantidad());

        List<ProductoDetalleDto> productos = ventaProducto.getProducto()
                .stream()
                .map(detalle -> {
                    ProductoDetalleDto productoDetalleDto = new ProductoDetalleDto();
                    Producto producto = detalle.getProductos();
                    productoDetalleDto.setIdproducto(producto.getIdproducto());
                    productoDetalleDto.setIdtipopro(producto.getTipoproducto() != null ? producto.getTipoproducto().getIdtipopro() : null);
                    productoDetalleDto.setNombre(producto.getNombre());
                    productoDetalleDto.setIdanimal(producto.getAnimal() != null ? producto.getAnimal().getIdanimal() : null);
                    return productoDetalleDto;
                })
                .collect(Collectors.toList());
        ventaProductoDto.setProduclist(productos);

        return ventaProductoDto;
    }



}
