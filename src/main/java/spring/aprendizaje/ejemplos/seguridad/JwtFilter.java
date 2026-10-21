package spring.aprendizaje.ejemplos.seguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        /* Se lee la cabecera Authorization, que debe venir como "Bearer <token>" */
        String authHeader = request.getHeader("Authorization");
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            /* substring(7) quita el prefijo "Bearer " y deja solo el token */
            String token = authHeader.substring(7);
            if (jwtService.esTokenValido(token)) {
                username = jwtService.extraerNombreUsuario(token);
            }
        }

        /* Si el token es válido y nadie ha autenticado ya esta petición, se registra al usuario */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        /* Siempre se continúa la cadena; si no hubo autenticación, Spring Security decidirá el rechazo */
        filterChain.doFilter(request, response);
    }
}