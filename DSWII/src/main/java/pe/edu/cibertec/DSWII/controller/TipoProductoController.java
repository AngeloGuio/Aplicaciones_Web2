package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DSWII.model.bd.Animal;
import pe.edu.cibertec.DSWII.model.bd.TipoProducto;
import pe.edu.cibertec.DSWII.service.AnimalService;
import pe.edu.cibertec.DSWII.service.ITipoProductoService;

import java.util.ArrayList;
import java.util.List;

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
}
