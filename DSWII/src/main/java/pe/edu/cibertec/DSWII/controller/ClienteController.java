package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Cliente;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.VentaProducto;
import pe.edu.cibertec.DSWII.model.dto.ClienteDto;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.dto.VentaProductoDto;
import pe.edu.cibertec.DSWII.service.IClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cliente")
public class ClienteController {
   private IClienteService clienteService;
    @GetMapping("/dto")
    public ResponseEntity<List<ClienteDto>> listarClienteDtoResponse() {
        List<Cliente> clientes = clienteService.clientelistar();
        List<ClienteDto> clientesDto = clientes.stream()
                .map(clienteService::ClienteconvertirADto)
                .collect(Collectors.toList());
        if (clientesDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientesDto, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<Cliente>> listResponseCliente(){
        List<Cliente> clienteList=new ArrayList<>(clienteService.clientelistar());
        if (clienteList.isEmpty()){return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(clienteList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Cliente> clienteResponseId(@PathVariable Integer id){
        Cliente clienteresponse=clienteService.buscarClienteID(id).orElseThrow(
                ()->new ResourceNotFoundException("El cliente con id "+id+" no existe.")
        );
        return new ResponseEntity<>(clienteresponse,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Cliente> clienteResponseagregar(@RequestBody Cliente cliente){
        Cliente nuevoCliente=clienteService.agregarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente,HttpStatus.OK);
    }



}
