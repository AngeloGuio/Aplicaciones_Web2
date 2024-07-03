package pe.edu.cibertec.DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.model.bd.Proveedor;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {
}
