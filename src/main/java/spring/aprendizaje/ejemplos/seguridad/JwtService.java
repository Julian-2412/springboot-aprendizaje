package spring.aprendizaje.ejemplos.seguridad;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    /* Clave secreta que firma los tokens (HS256). */
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    /* Duración del token: 24 horas en milisegundos */
    private static final long EXPIRATION_TIME = 86400000;

    /* Genera el token */
    public String generarToken(String nombreUsuario) {
        return Jwts.builder()
                .subject(nombreUsuario)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) /* Firma el token */
                .compact();
    }

    /* Extrae el nombre de usuario (guardado en el subject) */
    public String extraerNombreUsuario(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /* Valida el token: firma correcta y que no haya expirado */
    public boolean esTokenValido(String token) {
        try {
            Date expiration = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            /* Firma inválida, token mal formado o expirado */
            return false;
        }
    }
}