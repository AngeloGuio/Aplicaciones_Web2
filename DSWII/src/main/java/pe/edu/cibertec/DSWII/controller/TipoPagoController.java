package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
import pe.edu.cibertec.DSWII.service.ITipoPagoService;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/tipoPago")
public class TipoPagoController {
    private ITipoPagoService tipoPagoService;

    @GetMapping("")
    public ResponseEntity<List<TipoPago>> listResponseCliente(){
        List<TipoPago> tipoPagoList=new ArrayList<>(tipoPagoService.tipoPagoListar());
        if (tipoPagoList.isEmpty()){return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(tipoPagoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<TipoPago> clienteResponseId(@PathVariable Integer id){
        TipoPago tipoPagoResponse=tipoPagoService.buscarTipoPagoID(id).orElseThrow(
                ()->new ResourceNotFoundException("El cliente con id "+id+" no existe.")
        );
        return new ResponseEntity<>(tipoPagoResponse,HttpStatus.OK);
    }

}
