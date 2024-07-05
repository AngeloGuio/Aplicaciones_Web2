package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII.model.bd.Animal;
import pe.edu.cibertec.DSWII.service.AnimalService;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/animal")
public class AnimalController {

    private AnimalService animalService;

    @GetMapping("")
    public ResponseEntity<List<Animal>> listarAnimales(){
        List<Animal> animalList = new ArrayList<>(animalService.listarAnimales());
        if(animalList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(animalList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> obtenerAnimalXId(
            @PathVariable Integer id){
        Animal animal = animalService
                .obtenerAnimalxId(id).orElseThrow(
                        () -> new ResourceNotFoundException("El Animal con Id " +
                                id + " no existe"));
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Animal> registrarCategoria(
            @RequestBody Animal animal
    ){
        return new ResponseEntity<>(
                animalService.guardarAnimales(animal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> actualizarCategoria(
            @PathVariable Integer id,
            @RequestBody Animal animal
    ){
        Animal newAnimal = animalService.obtenerAnimalxId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Animal con Id "
                        + id +" no existe"));
        newAnimal.setDescanimal(animal.getDescanimal());

        return new ResponseEntity<>(
                animalService.guardarAnimales(newAnimal),
                HttpStatus.OK);
    }

}
