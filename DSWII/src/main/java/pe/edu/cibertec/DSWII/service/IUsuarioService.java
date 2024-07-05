package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> usuarioList();
    Optional<Usuario> buscarusuarioXID(Integer id);
    Usuario agregarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Integer id, Usuario usuario);
}
