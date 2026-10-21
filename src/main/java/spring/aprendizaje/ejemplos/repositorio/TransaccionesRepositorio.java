package spring.aprendizaje.ejemplos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import spring.aprendizaje.ejemplos.modelo.Transacciones;

public interface TransaccionesRepositorio extends JpaRepository<Transacciones, String> {
    List<Transacciones> findByUsuarioId(Long usuarioId);
} 
