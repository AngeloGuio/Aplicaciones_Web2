package pe.edu.cibertec.DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.DSWII.model.bd.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
}
