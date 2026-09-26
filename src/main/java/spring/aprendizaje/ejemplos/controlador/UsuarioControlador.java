package spring.aprendizaje.ejemplos.controlador;

import java.util.List;
import spring.aprendizaje.ejemplos.modelo.Usuario;
import spring.aprendizaje.ejemplos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsuarioControlador {

    @Autowired 
    UsuarioServicio usuarioServicio;

    @GetMapping("/list")
    public List<Usuario> getUsuarios() {
        return usuarioServicio.getUsuarios();
    }

    @GetMapping ("/List/{id}")
    public Usuario buscaroPorId(@PathVariable Long id) {
        return usuarioServicio.buscarUsuario(id);
    }

    @PostMapping ("/")
    public ResponseEntity<Usuario> agregar(@RequestBody Usuario usuario) {
        Usuario obj = usuarioServicio.nuevoUsuario(usuario);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping ("/")
    public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario) {
        Usuario obj = usuarioServicio.buscarUsuario(usuario.getId());
        if(obj != null) {
            obj.setCorreo(usuario.getCorreo());
            obj.setIdTipoDocumento(usuario.getIdTipoDocumento());
            obj.setNombre(usuario.getNombre());
            obj.setNombreUsuario(usuario.getNombreUsuario());
            obj.setNumeroDocumento(usuario.getNumeroDocumento());
            obj.setPassword(usuario.getPassword());
            usuarioServicio.nuevoUsuario(obj);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Usuario> eliminar(@PathVariable Long id) {
        Usuario obj = usuarioServicio.buscarUsuario(id);
        if(obj != null) {
            usuarioServicio.borrarUsuario(id);
        } else {
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}   