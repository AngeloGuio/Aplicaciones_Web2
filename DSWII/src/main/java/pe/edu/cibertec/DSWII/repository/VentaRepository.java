package pe.edu.cibertec.DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.DSWII.model.bd.Venta;

public interface VentaRepository  extends JpaRepository<Venta, Integer> {
}
