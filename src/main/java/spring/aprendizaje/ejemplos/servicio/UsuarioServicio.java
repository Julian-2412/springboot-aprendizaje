package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework. transaction.annotation.Transactional;
import spring.aprendizaje.ejemplos.modelo.Usuario;
import spring.aprendizaje.ejemplos.repositorio.UsuarioRepositorio;
import org.springframework.http.ResponseEntity;
import spring.aprendizaje.ejemplos.modelo.LoginDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus; 

@Service
@Transactional 
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override 
    public List<Usuario> getUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario nuevoUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario buscarUsuario(Long id) {
        Usuario usuario = null;
        usuario = usuarioRepositorio.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        }
        return usuario;
    }

    @Override 
    public int borrarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
        return 1;
    }

    @Override 
    public int login(LoginDto usuarioDto) {
        Long count = usuarioRepositorio.findByNombreUsuarioAndPassword(usuarioDto.getNombreUsuario(), usuarioDto.getPassword());
        return Math.toIntExact(count);
    }

    @Override
    public ResponseEntity<?> ingresar(LoginDto usuarioDto) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = null;
        try {
            usuario = usuarioRepositorio.findUsuarioByNombreUsuarioAndPassword(usuarioDto.getNombreUsuario(), usuarioDto.getPassword());
            if (usuario == null) {
                response.put("Usuario", null);
                response.put("mensaje", "Usuario o contraseña incorrectos");
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("Usuario", usuario);
                response.put("mensaje", "Inicio de sesión exitoso");
                response.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.put("Usuario", null);
            response.put("mensaje", "Error al iniciar sesión");
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   
}