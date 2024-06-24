package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Trabajador;

import java.util.List;
import java.util.Optional;

public interface ITrabajadorService {
    List<Trabajador> trabajadorList();
    Optional<Trabajador> buscarTrabajadorxID(int id);
    Trabajador agregarTrabajador(Trabajador trabajador);
}
