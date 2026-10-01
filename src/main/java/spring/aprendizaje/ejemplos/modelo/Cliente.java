package spring.aprendizaje.ejemplos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;     

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcliente;
    
    @NotBlank (message = "El campo nombres no puede estar vacío")
    @NotNull (message = "El campo nombres no puede ser nulo")
    @Size (min = 3, max = 70, message = "El campo nombres debe tener entre 3 y 70 caracteres")
    @Column (name = "nombres", nullable = false, length = 70)
    private String nombres;
    
    @NotBlank (message = "El campo apellidos no puede estar vacío")
    @NotNull (message = "El campo apellidos no puede ser nulo")
    @Size (min = 3, max = 70, message = "El campo apellidos debe tener entre 3 y 70 caracteres")
    @Column (name = "apellidos", nullable = false, length = 150)
    private String apellidos;
    
    @NotBlank (message = "El campo dirección no puede estar vacío")
    @NotNull (message = "El campo dirección no puede ser nulo")
    @Size (min = 3, max = 150, message = "El campo dirección debe tener entre 3 y 150 caracteres")
    @Column (name = "direccion", nullable = true, length = 150)
    private String direccion;
    
    @NotBlank (message = "El campo teléfono no puede estar vacío")
    @NotNull (message = "El campo teléfono no puede ser nulo")
    @Size (min = 10, max = 10, message = "El campo teléfono debe tener 10 caracteres")
    @Column (name = "telefono", nullable = true, length = 10)
    private String telefono;

    @NotNull 
    @NotBlank (message = "El campo email no puede estar vacío")    
    @Size (max = 150, message = "El campo email debe tener entre 3 y 150 caracteres")
    @Email (message = "El campo email debe ser un correo electrónico válido")
    @Column (name = "email", nullable = true, length = 150)
    private String email;

    public Integer getIdcliente() {
        return idcliente;
    }   

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}