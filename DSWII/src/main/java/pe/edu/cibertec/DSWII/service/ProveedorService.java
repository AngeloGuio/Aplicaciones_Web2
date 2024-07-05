package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Proveedor;
import pe.edu.cibertec.DSWII.repository.ProveedorRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ProveedorService implements IProveedorService{

    ProveedorRepository proveedorRepository;
    @Override
    public List<Proveedor> proveedorList() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> buscarProveedorPorID(int id) {
        Optional<Proveedor> proveedor=proveedorRepository.findById(id);
        if ((proveedor.isEmpty())){;return Optional.empty();}
           return proveedor;
    }

    @Override
    public Proveedor agregarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
}
