package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
import pe.edu.cibertec.DSWII.repository.TipoPagoRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TipoPagoService implements ITipoPagoService{
    private TipoPagoRepository tipoPagoRepository;

    @Override
    public List<TipoPago> tipoPagoListar() {
        return tipoPagoRepository.findAll();
    }

    @Override
    public Optional<TipoPago> buscarTipoPagoID(Integer id) {
        Optional<TipoPago> tipoPagoId=tipoPagoRepository.findById(id);
        if (tipoPagoId.isEmpty()){return  Optional.empty();}
        return tipoPagoId;
    }

    @Override
    public TipoPago agregarTipoPago(TipoPago tipoPago) {
        return tipoPagoRepository.save(tipoPago);
    }

    @Override
    public TipoPago actualizarTipoPago(Integer id, TipoPago tipoPago) {
        return tipoPago;
    }
}
