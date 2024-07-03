package pe.edu.cibertec.DSWII.service;

import pe.edu.cibertec.DSWII.model.bd.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
     List<Proveedor> proveedorList();
    Optional<Proveedor> buscarProveedorPorID(int id);
     Proveedor agregarProveedor(Proveedor proveedor);



}
