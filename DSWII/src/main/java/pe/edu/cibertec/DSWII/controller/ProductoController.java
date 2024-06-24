package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {
   private ProductoService productoService;
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
    @PostMapping("")
    public ResponseEntity<Producto> productoResponseagregar(@RequestBody Producto producto){
        Producto nuevoProducto=productoService.agregarProducto(producto);
        return new ResponseEntity<>(nuevoProducto,HttpStatus.OK);
    }
}
