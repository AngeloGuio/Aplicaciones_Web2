package pe.edu.cibertec.DSWII.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DSWII.Exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.Model.Base.Proveedor;
import pe.edu.cibertec.DSWII.Service.ProveedorService;

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
                ()-> new ResourceNotFoundException("El proveedor con el id"+id+"No existe")
        );
        return new ResponseEntity<>(proveedor,HttpStatus.OK);
    }
}
