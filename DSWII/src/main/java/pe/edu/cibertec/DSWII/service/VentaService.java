package pe.edu.cibertec.DSWII.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.ClienteDto;
import pe.edu.cibertec.DSWII.model.dto.DetallePagoDto;
import pe.edu.cibertec.DSWII.model.dto.DetalleVentaDto;
import pe.edu.cibertec.DSWII.model.dto.VentaDto;
import pe.edu.cibertec.DSWII.repository.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final TipoPagoRepository tipoPagoRepository;
    private final DetallePagoRepository detallePagoRepository;

    @Transactional
    public VentaDto registrarVentaConClienteNuevo(VentaDto ventaDto, ClienteDto clienteDto, DetallePagoDto detallePagoDto) {
        // Crear y guardar el nuevo cliente
        Cliente cliente = new Cliente();
        cliente.setNombres(clienteDto.getNombres());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setCorreo(clienteDto.getCorreo());
        cliente.setDni(clienteDto.getDni());
        cliente = clienteRepository.save(cliente);

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(Timestamp.valueOf(LocalDateTime.parse(ventaDto.getFecha())));
        venta.setDireccion(ventaDto.getDireccion());
        venta.setMontototal(ventaDto.getMontototal());

        TipoPago tipoPago = tipoPagoRepository.findById(ventaDto.getIdtipopago())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de pago no encontrado"));
        venta.setTipopago(tipoPago);

        // Guardar la venta para obtener el idventa generado
        venta = ventaRepository.save(venta);

        BigDecimal montototal = new BigDecimal("0.00");
        for (DetalleVentaDto detalleDto : ventaDto.getDetallesVenta()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(productoRepository.findById(detalleDto.getIdproducto())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado")));
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPrecio(detalleDto.getPrecio());

            BigDecimal subtotal = detalle.getPrecio().multiply(new BigDecimal(detalle.getCantidad()));
            montototal = montototal.add(subtotal);

            detalleVentaRepository.save(detalle);
        }

        venta.setMontototal(montototal);

        ventaRepository.save(venta);

        DetallePago detallePago = new DetallePago();
        detallePago.setVenta(venta);
        detallePago.setNumerotarjeta(detallePagoDto.getNumerotarjeta());
        detallePago.setFechavencimiento(Timestamp.valueOf(LocalDateTime.parse(detallePagoDto.getFechavencimiento())));
        detallePago.setCvv(detallePagoDto.getCvv());
        detallePagoRepository.save(detallePago);

        return convertirVentaAVentaDto(venta);
    }

    // Método para convertir una entidad Venta a VentaDto
    private VentaDto convertirVentaAVentaDto(Venta venta) {
        VentaDto ventaDto = new VentaDto();
        ventaDto.setIdventa(venta.getIdventa());
        ventaDto.setIdcliente(venta.getCliente().getIdcliente());
        ventaDto.setFecha(venta.getFecha().toString());
        ventaDto.setDireccion(venta.getDireccion());
        ventaDto.setMontototal(venta.getMontototal());
        ventaDto.setIdtipopago(venta.getTipopago().getIdtipopago());

        List<DetalleVentaDto> detallesDto = venta.getDetallesVenta().stream()
                .map(this::convertirDetalleVentaADetalleVentaDto)
                .collect(Collectors.toList());
        ventaDto.setDetallesVenta(detallesDto);

        return ventaDto;
    }

    // Método para convertir una entidad DetalleVenta a DetalleVentaDto
    private DetalleVentaDto convertirDetalleVentaADetalleVentaDto(DetalleVenta detalle) {
        DetalleVentaDto detalleDto = new DetalleVentaDto();
        detalleDto.setIddetalle(detalle.getIddetalle());
        detalleDto.setIdventa(detalle.getVenta().getIdventa());
        detalleDto.setIdproducto(detalle.getProducto().getIdproducto());
        detalleDto.setCantidad(detalle.getCantidad());
        detalleDto.setPrecio(detalle.getPrecio());

        return detalleDto;
    }

}