package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.TipoPago;

import java.util.List;
import java.util.Optional;

public interface ITipoPagoService {
    List<TipoPago> tipoPagoListar();
    Optional<TipoPago> buscarTipoPagoID(Integer id);
    TipoPago agregarTipoPago(TipoPago tipoPago);
    TipoPago actualizarTipoPago(Integer id,TipoPago tipoPago);
}
