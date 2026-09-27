package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import spring.aprendizaje.ejemplos.modelo.Cliente;

public interface IClienteServicio {
    
    public List<Cliente> getClientes();
    
    public Cliente getCliente(Integer id);
    
    public Cliente grabarCliente(Cliente cliente);

    public void delete(Integer id);
}