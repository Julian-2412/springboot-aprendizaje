package spring.aprendizaje.ejemplos.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* Se inyecta el filtro que valida el JWT */
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            /* CSRF se desactiva porque la API no usa cookies de sesión, sino tokens */
            .csrf(csrf -> csrf.disable())

            /* Sin sesiones en el servidor: cada petición debe traer su token */
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                /* Rutas públicas: el login y la documentación de Swagger */
                .requestMatchers("/login", "/", "/list").permitAll()
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                /* Todo lo demás requiere un JWT válido */
                .anyRequest().authenticated())

            /* El filtro JWT corre antes del filtro de usuario/contraseña de Spring */
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}