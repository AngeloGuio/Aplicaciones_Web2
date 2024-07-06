package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
import pe.edu.cibertec.DSWII.repository.TipoPagoRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class TipoPagoService implements ITipoPagoService {
    private TipoPagoRepository tipoPagoRepository;

    @Override
    public List<TipoPago> tipoPagoList() {
        return tipoPagoRepository.findAll();
    }
}