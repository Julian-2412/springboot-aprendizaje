package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import java.util.Optional;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.aprendizaje.ejemplos.modelo.Cliente;
import spring.aprendizaje.ejemplos.repositorio.IClienteRepositorio;

@Service
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private IClienteRepositorio clienteRepo;

    @Override 
    public List<Cliente>  findAll() {
        return clienteRepo.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepo.findById(id);
    }

    @Override
    public Cliente create(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        clienteRepo.deleteById(id);
    }
}