package spring.aprendizaje.ejemplos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "spring.aprendizaje.ejemplos.Controlador")
public class EjemplosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjemplosApplication.class, args);
	}

}
