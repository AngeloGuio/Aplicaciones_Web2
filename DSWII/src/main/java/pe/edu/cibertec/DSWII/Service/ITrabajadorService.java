package pe.edu.cibertec.DSWII.Service;

import pe.edu.cibertec.DSWII.Model.Base.Trabajador;

import java.util.List;
import java.util.Optional;

public interface ITrabajadorService {
    List<Trabajador> trabajadorList();
    Optional<Trabajador> buscarTrabajadorxID(int id);
    Trabajador agregarTrabajador(Trabajador trabajador);
}
