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

    @PostMapping("/dto")
    public ResponseEntity<ProductoDto> createProductoDto(@RequestBody ProductoDto productoDto) throws InstantiationException, IllegalAccessException {
        TipoProducto tipoProducto = tipoProductoRepository.findById(productoDto.getIdtipopro()).orElse(null);
        Proveedor proveedor = proveedorRepository.findById(productoDto.getIdproveedor()).orElse(null);
        Estado estado = estadoRepository.findById(productoDto.isCodestado() ? 1 : 0).orElse(null); // Convertir Boolean a Integer
        Animal animal = animalRepository.findById(productoDto.getIdanimal()).orElse(null);

        if (tipoProducto == null || proveedor == null || estado == null || animal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Producto producto = (Producto) dtoUtil.convertirAEntity(productoDto, Producto.class);
        producto.setIdtipopro(tipoProducto);
        producto.setProveedor(proveedor);
        producto.setEstado(estado);
        producto.setAnimal(animal);

        Producto savedProducto = productoService.agregarProducto(producto);
        ProductoDto savedProductoDto = (ProductoDto) dtoUtil.convertirADto(savedProducto, ProductoDto.class.newInstance());

        return new ResponseEntity<>(savedProductoDto, HttpStatus.CREATED);
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

    @PutMapping("/dto/{id}")
    public ResponseEntity<ProductoDto> updateProductoDto(@PathVariable Integer id, @RequestBody ProductoDto productoDto) throws InstantiationException, IllegalAccessException {
        // Encontrar el producto existente por ID
        Producto existingProducto = productoService.findProductoById(id);
        if (existingProducto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Obtener las entidades relacionadas
        TipoProducto tipoProducto = tipoProductoRepository.findById(productoDto.getIdtipopro()).orElse(null);
        Proveedor proveedor = proveedorRepository.findById(productoDto.getIdproveedor()).orElse(null);
        Estado estado = estadoRepository.findById(productoDto.isCodestado() ? 1 : 0).orElse(null);
        Animal animal = animalRepository.findById(productoDto.getIdanimal()).orElse(null);

        // Verificar que todas las entidades relacionadas existen
        if (tipoProducto == null || proveedor == null || estado == null || animal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Actualizar los campos del producto existente
        existingProducto.setNombre(productoDto.getNombre());
        existingProducto.setCantidad(productoDto.getCantidad());
        existingProducto.setPreciopublico(productoDto.getPreciopublico());
        existingProducto.setStockminimo(productoDto.getStockminimo());
        existingProducto.setStockmaximo(productoDto.getStockmaximo());
        existingProducto.setPrecioproveedor(productoDto.getPrecioproveedor());
        existingProducto.setIdtipopro(tipoProducto);
        existingProducto.setProveedor(proveedor);
        existingProducto.setEstado(estado);
        existingProducto.setAnimal(animal);

        // Guardar el producto actualizado
        Producto updatedProducto = productoService.agregarProducto(existingProducto);
        ProductoDto updatedProductoDto = (ProductoDto) dtoUtil.convertirADto(updatedProducto, ProductoDto.class.newInstance());

        return new ResponseEntity<>(updatedProductoDto, HttpStatus.OK);
    }
}
