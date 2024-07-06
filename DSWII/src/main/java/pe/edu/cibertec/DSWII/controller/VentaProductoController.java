package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.VentaProducto;
import pe.edu.cibertec.DSWII.model.dto.DtoEntity;
import pe.edu.cibertec.DSWII.model.dto.VentaProductoDto;
import pe.edu.cibertec.DSWII.service.IVentaProductoService;
import pe.edu.cibertec.DSWII.service.VentaProductoService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/ventaproducto")
public class VentaProductoController {

    private VentaProductoService ventaProductoService;

    private IVentaProductoService iVentaProductoService;

    @GetMapping("")
    public ResponseEntity<List<VentaProducto>> listResponseVentaproducto(){
        List<VentaProducto> ventaproductoList=new ArrayList<>(ventaProductoService.ventaproductoList());
        if (ventaproductoList.isEmpty()){return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(ventaproductoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<VentaProducto> ventaproductoResponseId(@PathVariable Integer id){
        VentaProducto ventaproductoresponse=ventaProductoService.buscarVentaXID(id).orElseThrow(
                ()->new ResourceNotFoundException("El producto con id "+id+" no existe.")
        );
        return new ResponseEntity<>(ventaproductoresponse,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<VentaProducto> ventaproductoResponseagregar(@RequestBody VentaProducto ventaproducto){
        VentaProducto nuevoVentaProducto=ventaProductoService.agregarVenta(ventaproducto);
        return new ResponseEntity<>(nuevoVentaProducto,HttpStatus.OK);
    }


    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVentasYDetalleTipoPago(@RequestBody VentaProductoDto ventaProductoDto) {
        boolean resultado = ventaProductoService.registrarVentasYDetalleTipoPago(ventaProductoDto);
        if (resultado) {
            return ResponseEntity.ok("Venta registrada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la venta");
        }
    }

    /*@GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarVentaProductosDto(){
        List<DtoEntity> ventaproductDtoList = new ArrayList<>();
        ventaproductDtoList = ventaProductoService.ventaproductoList()
                .stream()
                .map(x -> new DtoUtil().convertirADto(x, new VentaProductoDto()))
                .collect(Collectors.toList());
        if(ventaproductDtoList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ventaproductDtoList, HttpStatus.OK);
    }
*/

    @GetMapping("/dto")
    public ResponseEntity<List<VentaProductoDto>> listarVentasDto() {
        List<VentaProducto> ventas = ventaProductoService.ventaproductoList();
        List<VentaProductoDto> ventasDto = ventas.stream()
                .map(ventaProductoService::convertirADto)
                .collect(Collectors.toList());
        if (ventasDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ventasDto, HttpStatus.OK);
    }



    @GetMapping("/dto/{id}")
    public ResponseEntity<VentaProductoDto> obtenerVentaProductoDtoPorId(@PathVariable Integer id) {
        Optional<VentaProducto> ventaProductoOpt = ventaProductoService.buscarVentaXID(id);

        if (ventaProductoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        VentaProducto ventaProducto = ventaProductoOpt.get();
        VentaProductoDto ventaProductoDto = ventaProductoService.convertirADto(ventaProducto);
        return new ResponseEntity<>(ventaProductoDto, HttpStatus.OK);
    }



}
