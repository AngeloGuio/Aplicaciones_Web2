package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DSWII.model.bd.Animal;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
import pe.edu.cibertec.DSWII.service.AnimalService;
import pe.edu.cibertec.DSWII.service.ITipoPagoService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/tipopago")
public class TipoPagoController {
    private ITipoPagoService tipoPagoService;

    @GetMapping("")
    public ResponseEntity<List<TipoPago>> listarTipoPagoResponse(){
        List<TipoPago> tipopagoList = new ArrayList<>(tipoPagoService.tipoPagoList());
        if(tipopagoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipopagoList, HttpStatus.OK);
    }
}
