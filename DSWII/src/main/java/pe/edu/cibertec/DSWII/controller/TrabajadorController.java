package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Trabajador;
import pe.edu.cibertec.DSWII.service.TrabajadorService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/trabajador")
public class TrabajadorController {
    private TrabajadorService trabajadorService;
    @GetMapping("")
    public ResponseEntity<List<Trabajador>> listResponseEntity(){
        List<Trabajador> trabajadors=new ArrayList<>(trabajadorService.trabajadorList());
        if (trabajadors.isEmpty()){new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(trabajadors,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> buscarTrabajadorId(@PathVariable Integer id){
        Trabajador trabajador=trabajadorService.buscarTrabajadorxID(id).orElseThrow(
                ()->new ResourceNotFoundException("El trabajador con el id "+id+" no existe.")
        );
        return new ResponseEntity<>(trabajador,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Trabajador> nuevoTrabajador(@RequestBody Trabajador trabajador){
        Trabajador trabajadors=trabajadorService.agregarTrabajador(trabajador);
        return new ResponseEntity<>(trabajadors,HttpStatus.CREATED);
    }
}
