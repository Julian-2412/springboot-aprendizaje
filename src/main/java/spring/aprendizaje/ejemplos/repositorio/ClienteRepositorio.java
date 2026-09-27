package spring.aprendizaje.ejemplos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.aprendizaje.ejemplos.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    
}
