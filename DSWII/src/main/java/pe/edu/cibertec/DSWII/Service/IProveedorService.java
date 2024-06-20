package pe.edu.cibertec.DSWII.Service;

import pe.edu.cibertec.DSWII.Model.Base.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
     List<Proveedor> proveedorList();
    Optional<Proveedor> buscarProveedorPorID(int id);
     Proveedor agregarProveedor(Proveedor proveedor);



}
