package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Animal;

import java.util.List;
import java.util.Optional;

public interface IAnimalService {
    List<Animal> listarAnimales();
    Animal guardarAnimales(Animal animal);
    Optional<Animal> obtenerAnimalxId(Integer id);
}
