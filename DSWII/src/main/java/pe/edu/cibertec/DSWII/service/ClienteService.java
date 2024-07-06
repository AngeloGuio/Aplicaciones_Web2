package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Cliente;
import pe.edu.cibertec.DSWII.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ClienteService implements IClienteService{
    private ClienteRepository clienteRepository;
    @Override
    public List<Cliente> clientelistar() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarClienteID(Integer id) {
        Optional<Cliente> clienteid=clienteRepository.findById(id);
        if (clienteid.isEmpty()){return  Optional.empty();}
        return clienteid;
    }

    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        return cliente;
    }
}
