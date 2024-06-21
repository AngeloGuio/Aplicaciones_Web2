package pe.edu.cibertec.DSWII.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.Model.Base.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,Integer> {
}
