package pe.edu.cibertec.DSWII.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.Model.Base.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario,Integer> {
}
