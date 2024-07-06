package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
import pe.edu.cibertec.DSWII.service.TipoPagoService;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/tipoPago")
public class TipoPagoController {
    private TipoPagoService tipoPagoService;

    @GetMapping("")
    public ResponseEntity<List<TipoPago>> listResponseCliente(){
        List<TipoPago> tipoPagoList=new ArrayList<>(tipoPagoService.tipoPagoList());
        if (tipoPagoList.isEmpty()){return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(tipoPagoList,HttpStatus.OK);
    }


}
