package pe.edu.cibertec.DSWII.service;

import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII.model.bd.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> clientelistar();
    Optional<Cliente> buscarClienteID(Integer id);
    Cliente agregarCliente(Cliente cliente);
    Cliente actualizarCliente(Integer id,Cliente cliente);
}
