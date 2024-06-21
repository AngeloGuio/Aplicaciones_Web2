package pe.edu.cibertec.DSWII.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.Model.Base.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
