package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import spring.aprendizaje.ejemplos.modelo.Usuario;

public interface IUsuarioServicio {
    
    List<Usuario> getUsuarios();
    
    Usuario nuevoUsuario(Usuario usuario);
    
    Usuario buscarUsuario(Long id);

    int borrarUsuario(Long id);
}