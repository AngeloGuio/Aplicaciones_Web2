package pe.edu.cibertec.DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII.model.bd.Cliente;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.bd.VentaProducto;
import pe.edu.cibertec.DSWII.model.dto.ClienteDto;
import pe.edu.cibertec.DSWII.model.dto.ProductoDto;
import pe.edu.cibertec.DSWII.model.dto.VentaProductoDto;
import pe.edu.cibertec.DSWII.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Override
    public ClienteDto ClienteconvertirADto(Cliente cliente) {
        ClienteDto clientedto = new ClienteDto();
        clientedto.setIdcliente(cliente.getIdcliente());
        clientedto.setNombres(cliente.getNombres());
        clientedto.setApellidos(cliente.getApellidos());
        clientedto.setCorreo(cliente.getCorreo());
        return clientedto;
    }
}
