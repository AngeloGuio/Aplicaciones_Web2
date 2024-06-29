package pe.edu.cibertec.DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.model.dto.VentaProducto;

@Repository
public interface VentaProductoRepository extends JpaRepository<VentaProducto, Integer> {
}
