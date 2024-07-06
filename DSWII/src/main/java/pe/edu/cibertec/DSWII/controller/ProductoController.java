package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.DtoEntity;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.response.ApiResponse;
import pe.edu.cibertec.DSWII.repository.*;
import pe.edu.cibertec.DSWII.service.IProductoService;
import pe.edu.cibertec.DSWII.service.ProductoService;
import pe.edu.cibertec.DSWII.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {

   private ProductoService productoService;

   private IProductoService iProductoService;



    @GetMapping("")
    public ResponseEntity<List<Producto>> listResponseproducto(){
        List<Producto> productoList=new ArrayList<>(productoService.productoList());
        if (productoList.isEmpty()){return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(productoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Producto> productoResponseId(@PathVariable Integer id){
        Producto productoresponse=productoService.buscarProductoXID(id).orElseThrow(
                ()->new ResourceNotFoundException("El producto con id "+id+" no existe.")
        );
        return new ResponseEntity<>(productoresponse,HttpStatus.OK);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoDtoPorId(@PathVariable Integer id) {
        Optional<Producto> productoOpt = productoService.buscarProductoXID(id);

        if (productoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Producto producto = productoOpt.get();
        ProductoDto productoDto = new DtoUtil().convertirADto(producto, new ProductoDto());
        return new ResponseEntity<>(productoDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> productoResponseagregar(@RequestBody Producto producto){
        Producto nuevoProducto=productoService.agregarProducto(producto);
        return new ResponseEntity<>(nuevoProducto,HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse> registrarProducto(@RequestBody ProductoDto productoDto) {
        boolean resultado = productoService.registraryActualizarProducto(productoDto);
        if (resultado) {
            return ResponseEntity.ok(new ApiResponse(true, "Producto registrado exitosamente"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error al registrar el producto"));
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ApiResponse> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDto productoDto) {
        productoDto.setIdproducto(id);
        boolean resultado = productoService.registraryActualizarProducto(productoDto);
        if (resultado) {
            return ResponseEntity.ok(new ApiResponse(true, "Producto actualizado exitosamente"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error al actualizar el producto"));
        }
    }

    @GetMapping("/dto")
    public ResponseEntity<List<ProductoDto>> listarProductoDto() {
        List<ProductoDto> productDtoList = productoService.productoList()
                .stream()
                .map(producto -> new DtoUtil().convertirADto(producto, new ProductoDto()))
                .collect(Collectors.toList());

        if (productDtoList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

}
