package pe.edu.cibertec.DSWII.Service;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import pe.edu.cibertec.DSWII.Model.Base.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> usuarioList();
    Optional<Usuario> buscarusuarioXID(Integer id);
    Usuario agregarUsuario(Usuario usuario);
}
