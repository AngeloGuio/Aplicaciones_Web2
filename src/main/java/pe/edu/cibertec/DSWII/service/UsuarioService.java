package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Usuario;
import pe.edu.cibertec.DSWII.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
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

    @Override
    public Usuario obtenerUsuarioPorNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
}
