package pe.edu.cibertec.DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {
}
