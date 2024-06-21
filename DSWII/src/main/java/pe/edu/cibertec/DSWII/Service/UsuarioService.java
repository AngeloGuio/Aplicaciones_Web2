package pe.edu.cibertec.DSWII.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.Model.Base.Usuario;
import pe.edu.cibertec.DSWII.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService{
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> usuarioList() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarusuarioXID(Integer id) {
        Optional<Usuario> usuarioOptional=usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()){ return  Optional.empty();}
        return usuarioOptional;
    }

    @Override
    public Usuario agregarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
