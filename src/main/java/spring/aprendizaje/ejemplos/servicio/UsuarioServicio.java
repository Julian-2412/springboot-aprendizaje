package spring.aprendizaje.ejemplos.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework. transaction.annotation.Transactional;
import spring.aprendizaje.ejemplos.modelo.Usuario;
import spring.aprendizaje.ejemplos.repositorio.TransaccionesRepositorio;
import spring.aprendizaje.ejemplos.repositorio.UsuarioRepositorio;
import spring.aprendizaje.ejemplos.seguridad.JwtService;

import org.springframework.http.ResponseEntity;
import spring.aprendizaje.ejemplos.modelo.LoginDto;
import spring.aprendizaje.ejemplos.modelo.Transacciones;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus; 

@Service
@Transactional 
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private TransaccionesRepositorio transaccionesRepositorio;


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
                String token = jwtService.generarToken(usuario.getNombreUsuario());

                response.put("token", token);
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

    @Override 
    public List<Transacciones> obtenerTransaccionesPorUsuario(Long usuarioId){
        return transaccionesRepositorio.findByUsuarioId(usuarioId);
    }

    @Override 
    public List<Transacciones> guardarTransaccionesDeUsuario(Long usuarioId, List<Transacciones> transacciones) {
        if (transacciones == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se requiere una lista de transacciones");
        }

        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el usuario con id " + usuarioId));

        for (Transacciones transaccion : transacciones) {
            if (transaccion == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "La lista no puede contener transacciones nulas");
            }
            transaccion.setUsuario(usuario);
        }

        return transaccionesRepositorio.saveAll(transacciones);
    }
}