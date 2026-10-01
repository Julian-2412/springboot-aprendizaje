package spring.aprendizaje.ejemplos.controlador;

import java.util.List;

import spring.aprendizaje.ejemplos.modelo.Cliente;
import spring.aprendizaje.ejemplos.servicio.ClienteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    @Autowired 
    private ClienteServicio clienteService;

    @Operation (summary = "Obtener todos los clientes", description = "Obtiene una lista de todos los clientes registrados en el sistema.")
    @GetMapping 
    public ResponseEntity<List<Cliente>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Operation (summary = "Obtener cliente por ID", description = "Obtiene un cliente específico según su ID. Devuelve un error 404 si el cliente no existe.")
    @GetMapping ("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Integer idCliente) {
        return clienteService.findById(idCliente).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @Operation (summary = "Crear cliente", description = "Crea un nuevo cliente en el sistema.")
    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.create(cliente), HttpStatus.CREATED);
    }

    @Operation (summary = "Actualizar cliente", description = "Actualiza la información de un cliente existente.")
    @PutMapping
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente) {
        return clienteService.findById(cliente.getIdcliente()).map(c -> ResponseEntity.ok(clienteService.update(cliente))).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation (summary = "Eliminar cliente", description = "Elimina un cliente existente del sistema.")
    @DeleteMapping ("/{id}")
    public ResponseEntity<Cliente> delete(Integer idCliente) {
        return clienteService.findById(idCliente).map(c -> {
            clienteService.delete(idCliente);
            return ResponseEntity.ok(c);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}