package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Animal;
import pe.edu.cibertec.DSWII.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class AnimalService implements IAnimalService{

    private AnimalRepository animalRepository;


    @Override
    public List<Animal> listarAnimales() {
        return animalRepository.findAll();
    }

    @Override
    public Animal guardarAnimales(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Optional<Animal> obtenerAnimalxId(Integer id) {
        Optional<Animal> category
                = animalRepository.findById(id);
        if(category.isEmpty()){
            return Optional.empty();
        }
        return category;
    }
}
