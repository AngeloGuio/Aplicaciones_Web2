package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Proveedor;
import pe.edu.cibertec.DSWII.service.ProveedorService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/proveedor")
public class ProveedorController {
    private ProveedorService proveedorService;
    @GetMapping("/proveedorr")
    public ResponseEntity<?> getProveedor() {
        // lógica para manejar la solicitud
        return ResponseEntity.ok("Proveedor encontrado");
    }
    @GetMapping("")
    public ResponseEntity<List<Proveedor>> listProveedor(){
        List<Proveedor> proveedorList=new ArrayList<>(proveedorService.proveedorList());
        if (proveedorList.isEmpty()){return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(proveedorList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> BuscarProveedorxID(@PathVariable Integer id){
        Proveedor proveedor=proveedorService.buscarProveedorPorID(id).orElseThrow(
                ()-> new ResourceNotFoundException("El proveedor con el id "+id+" no existe.")
        );
        return new ResponseEntity<>(proveedor,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Proveedor> agregarProveedor(@RequestBody Proveedor proveedor){
        return new ResponseEntity<>(proveedorService.agregarProveedor(proveedor),HttpStatus.CREATED);

    }
    
}
