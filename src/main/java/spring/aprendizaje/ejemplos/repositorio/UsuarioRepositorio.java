package spring.aprendizaje.ejemplos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.aprendizaje.ejemplos.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    @Query("select count(p) from Usuario p where p.nombreUsuario = :nombreUsuario and p.password = :password")
    Long findByNombreUsuarioAndPassword(@Param("nombreUsuario") String nombreUsuario, @Param("password") String password);

    @Query("select p from Usuario p where p.nombreUsuario = :nombreUsuario and p.password = :password")
    Usuario findUsuarioByNombreUsuarioAndPassword(@Param("nombreUsuario") String nombreUsuario, @Param("password") String password);
}
