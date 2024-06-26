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
import pe.edu.cibertec.DSWII.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarVentasProductosDto(){
        List<DtoEntity> ventaproductDtoList = new ArrayList<>();
        ventaproductDtoList = iVentaProductoService.ventaproductoList()
                .stream()
                .map(x -> new DtoUtil().convertirADto(x, new VentaProductoDto()))
                .collect(Collectors.toList());
        if(ventaproductDtoList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ventaproductDtoList, HttpStatus.OK);
    }

}
