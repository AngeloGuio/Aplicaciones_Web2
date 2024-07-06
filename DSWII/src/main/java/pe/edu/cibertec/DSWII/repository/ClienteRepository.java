package pe.edu.cibertec.DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.DSWII.model.bd.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
