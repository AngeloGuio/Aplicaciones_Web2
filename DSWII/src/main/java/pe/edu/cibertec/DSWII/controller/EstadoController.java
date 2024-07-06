package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DSWII.model.bd.Animal;
import pe.edu.cibertec.DSWII.model.bd.Estado;
import pe.edu.cibertec.DSWII.service.IEstadoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/estado")
public class EstadoController {
    private IEstadoService estadoService;
    @GetMapping("")
    public ResponseEntity<List<Estado>> listarEstadosResponse(){
        List<Estado> estadoList = new ArrayList<>(estadoService.listarEstado());
        if(estadoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(estadoList, HttpStatus.OK);
    }
}
