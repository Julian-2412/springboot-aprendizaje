package spring.aprendizaje.ejemplos.Controlador;

import spring.aprendizaje.ejemplos.Modelo.Curso;

import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CursoControlador {

    @GetMapping(value = "/curso", produces = MediaType.APPLICATION_JSON_VALUE)
    public Curso getCurso() {
        return new Curso("Java", 100, "Mañana");
    }

    private List<Curso> cursos;
    @PostConstruct 
    public void init() {
        cursos = new ArrayList<>();
        cursos.add(new Curso("Spring", 25, "Tarde"));
        cursos.add(new Curso("SpringBoot", 20, "Tarde"));
        cursos.add(new Curso("Python", 30, "Tarde"));
        cursos.add(new Curso("Java EE", 50, "Fin de semana"));
        cursos.add(new Curso("Java básico", 30, "Mañana"));
    }

    @GetMapping (value = "/cursos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> getCursos() {
        return cursos;
    }

    @GetMapping (value = "/cursos/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> buscarCursos(@PathVariable("nombre") String nombre) {
        List<Curso> cursosEncontrados = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getNombre().contains(nombre)) {
                cursosEncontrados.add(curso);
            }
        }
        return cursosEncontrados;
    }

    @PostMapping(value = "/curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> agregarCurso(@RequestBody Curso curso) {
        cursos.add(curso);
        return cursos;
    }

    @PutMapping(value = "/curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> actualizarCurso(@RequestBody Curso curso) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getNombre().equals(curso.getNombre())) {
                cursos.set(i, curso);
                break;
            }
        }
        return cursos;
    }

    @DeleteMapping(value = "/curso/{name}")
    public void eliminarCurso(@PathVariable("name") String nombre) {
        cursos.removeIf(curso -> curso.getNombre().equals(nombre));
    }
}   