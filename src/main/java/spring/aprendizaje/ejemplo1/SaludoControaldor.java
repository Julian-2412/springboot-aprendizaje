package spring.aprendizaje.ejemplo1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SaludoControaldor {
    
    @GetMapping (value = "/saludo/{titulo}/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String Saludo(@PathVariable("titulo") String t, @PathVariable("name") String n) {
        return "Bienvenido " + t + " " + n + " al curso de Spring Boot";
    }
}
