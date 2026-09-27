package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import java.util.Optional;

import spring.aprendizaje.ejemplos.modelo.Cliente;

public interface IClienteServicio {
    
    public List<Cliente> findAll();
    
    public Optional<Cliente> findById(Integer id);
    
    public Cliente create(Cliente cliente);

    public Cliente update(Cliente cliente);

    public void delete(Integer id);
}