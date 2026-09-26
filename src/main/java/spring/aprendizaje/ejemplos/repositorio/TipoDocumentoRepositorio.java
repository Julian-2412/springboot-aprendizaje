package spring.aprendizaje.ejemplos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.aprendizaje.ejemplos.modelo.Tipodocumento;

public interface TipoDocumentoRepositorio extends JpaRepository<Tipodocumento, Long> {

    
} 
