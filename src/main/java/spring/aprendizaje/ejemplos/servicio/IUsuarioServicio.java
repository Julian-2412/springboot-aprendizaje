package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import org.springframework.http.ResponseEntity;
import spring.aprendizaje.ejemplos.modelo.LoginDto;
import spring.aprendizaje.ejemplos.modelo.Usuario;

public interface IUsuarioServicio {
    
    List<Usuario> getUsuarios();
    
    Usuario nuevoUsuario(Usuario usuario);
    
    Usuario buscarUsuario(Long id);

    int borrarUsuario(Long id);

    int login(LoginDto usuarioDto);

    ResponseEntity<?> ingresar(LoginDto usuarioDto);
}