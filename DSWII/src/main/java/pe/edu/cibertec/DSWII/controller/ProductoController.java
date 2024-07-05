package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.DtoEntity;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.repository.*;
import pe.edu.cibertec.DSWII.service.IProductoService;
import pe.edu.cibertec.DSWII.service.ProductoService;
import pe.edu.cibertec.DSWII.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {

   private ProductoService productoService;

   private IProductoService iProductoService;

    private ProductoRepository productoRepository;

    private TipoProductoRepository tipoProductoRepository;

    private ProveedorRepository proveedorRepository;

    private EstadoRepository estadoRepository;

    private AnimalRepository animalRepository;

    private DtoUtil dtoUtil;


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

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarProducto(@RequestBody ProductoDto productoDto) {
        boolean resultado = productoService.registrarProducto(productoDto);
        if (resultado) {
            return ResponseEntity.ok("Producto registrado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el producto");
        }
    }
   @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarProductosDto(){
        List<DtoEntity> productDtoList = new ArrayList<>();
        productDtoList = iProductoService.productoList()
                .stream()
                .map(x -> new DtoUtil().convertirADto(x, new ProductoDto()))
                .collect(Collectors.toList());
        if(productDtoList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }


}
