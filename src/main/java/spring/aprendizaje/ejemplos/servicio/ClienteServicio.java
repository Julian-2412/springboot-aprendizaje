package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import jakarta.transaction.Transactional;   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.aprendizaje.ejemplos.modelo.Cliente;
import spring.aprendizaje.ejemplos.repositorio.ClienteRepositorio;

@Service
@Transactional 
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepo;

    @Override 
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente getCliente(Integer id) {
        return clienteRepo.findById(id).orElse(null);
    }

    @Override
    public Cliente grabarCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        clienteRepo.deleteById(id);
    }
}