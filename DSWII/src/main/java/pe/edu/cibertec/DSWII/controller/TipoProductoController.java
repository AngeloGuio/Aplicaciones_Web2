package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Animal;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.TipoProducto;
import pe.edu.cibertec.DSWII.model.bd.Usuario;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.dto.TipoProductoDto;
import pe.edu.cibertec.DSWII.model.response.ApiResponse;
import pe.edu.cibertec.DSWII.service.AnimalService;
import pe.edu.cibertec.DSWII.service.ITipoProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/tipoproducto")
public class TipoProductoController {
    private ITipoProductoService tipoProductoService;

    @GetMapping("")
    public ResponseEntity<List<TipoProducto>> listatipoProductoResponse(){
        List<TipoProducto> tipoproductoList = new ArrayList<>(tipoProductoService.listartipoProducto());
        if(tipoproductoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipoproductoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProducto> buscarIdResponseTipoProducto(@PathVariable Integer id){
        TipoProducto tipoProductoId=tipoProductoService.buscarTipoProductoXID(id).orElseThrow(
                ()->new ResourceNotFoundException("El tipo producto con el id "+id+" no existe")
        );
        return new ResponseEntity<>(tipoProductoId,HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> nuevoResponseTipoProducto(@RequestBody TipoProducto tipoProducto){
        try {
            TipoProducto nuevoTipoProducto = tipoProductoService.agregarTipoProducto(tipoProducto);
            return ResponseEntity.ok(new ApiResponse(true, "Tipo Producto creado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error al crear el tipo producto: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizarResponseTipoProducto(@PathVariable Integer id, @RequestBody TipoProducto tipoProducto){
        try {
            TipoProducto actualizarTipoProducto = tipoProductoService.actualizarTipoProducto(id, tipoProducto);
            return ResponseEntity.ok(new ApiResponse(true, "Tipo Producto actualizado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error al actualizar el tipo producto: " + e.getMessage()));
        }
    }
}
