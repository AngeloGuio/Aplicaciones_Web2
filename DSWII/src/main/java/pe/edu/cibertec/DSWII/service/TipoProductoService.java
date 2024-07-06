package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.*;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.dto.TipoProductoDto;
import pe.edu.cibertec.DSWII.repository.TipoProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TipoProductoService implements ITipoProductoService{

    private TipoProductoRepository tipoProductoRepository;


    @Override
    public List<TipoProducto> listartipoProducto() {
        return tipoProductoRepository.findAll();
    }

    @Override
    public Optional<TipoProducto> buscarTipoProductoXID(Integer id) {
        Optional<TipoProducto> tipoProductoOptional=tipoProductoRepository.findById(id);
        if (tipoProductoOptional.isEmpty()){return  Optional.empty();}
        return tipoProductoOptional;
    }

    @Override
    public TipoProducto agregarTipoProducto(TipoProducto tipoProducto) {
        return tipoProductoRepository.save(tipoProducto);
    }

    @Override
    public TipoProducto findTipoProductoById(Integer id) {
        return null;
    }

    @Override
    public TipoProducto actualizarTipoProducto(Integer id, TipoProducto tipoProductoActualizado){
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo producto con ID " + id + " no existe"));

        tipoProducto.setDesctipopro(tipoProductoActualizado.getDesctipopro());
        return tipoProductoRepository.save(tipoProducto);
    }

}
