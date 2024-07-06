package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Estado;
import pe.edu.cibertec.DSWII.repository.EstadoRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class EstadoService implements IEstadoService{
    private EstadoRepository estadoRepository;
    @Override
    public List<Estado> listarEstado() {
        return estadoRepository.findAll();
    }
}
