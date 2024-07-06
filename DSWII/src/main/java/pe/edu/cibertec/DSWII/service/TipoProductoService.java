package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.TipoPago;
import pe.edu.cibertec.DSWII.model.bd.TipoProducto;
import pe.edu.cibertec.DSWII.repository.TipoProductoRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class TipoProductoService implements ITipoProductoService{

    private TipoProductoRepository tipoProductoRepository;


    @Override
    public List<TipoProducto> listartipoProducto() {
        return tipoProductoRepository.findAll();
    }
}
