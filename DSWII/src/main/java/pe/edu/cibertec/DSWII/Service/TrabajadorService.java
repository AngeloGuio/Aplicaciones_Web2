package pe.edu.cibertec.DSWII.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.Model.Base.Trabajador;
import pe.edu.cibertec.DSWII.Repository.TrabajadorRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor

public class TrabajadorService implements ITrabajadorService {
     TrabajadorRepository trabajadorRepository;
    @Override
    public List<Trabajador> trabajadorList() {
        return trabajadorRepository.findAll();
    }

    @Override
    public Optional<Trabajador> buscarTrabajadorxID(int id) {
        Optional<Trabajador> trabajador= trabajadorRepository.findById(id);
        if (trabajador.isEmpty()){Optional.empty();}
        return trabajador;
    }

    @Override
    public Trabajador agregarTrabajador(Trabajador trabajador) {

        return trabajadorRepository.save(trabajador);
    }
}
