package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Usuario;
import pe.edu.cibertec.DSWII.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;
    @GetMapping("")
    public ResponseEntity<List<Usuario>> listResponseUsuario(){
        List<Usuario> usuarioList=new ArrayList<>(usuarioService.usuarioList());
        if (usuarioList.isEmpty()){return  new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return  new ResponseEntity<>(usuarioList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarIdResponseUsuario(@PathVariable Integer id){
        Usuario usuarioid=usuarioService.buscarusuarioXID(id).orElseThrow(
                ()->new ResourceNotFoundException("El usario con el id "+id+" no existe")
        );
        return new ResponseEntity<>(usuarioid,HttpStatus.OK);

    }



    @PostMapping("/registrar")
    public ResponseEntity<Usuario> nuevoResponseUsuario(@RequestBody Usuario usuario){
        Usuario nuevousuario=usuarioService.agregarUsuario(usuario);
        return new ResponseEntity<>(nuevousuario,HttpStatus.CREATED);

    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<Usuario> actualizarResponseUsuario(@RequestBody Integer id ,@RequestBody Usuario usuario){
        Usuario actualizarusuario=usuarioService.actualizarUsuario(id, usuario);
        return new ResponseEntity<>(actualizarusuario,HttpStatus.CREATED);

    }
}
