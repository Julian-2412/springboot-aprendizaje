    package spring.aprendizaje.ejemplos.modelo;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.Table;     
    import jakarta.persistence.CascadeType;
    import jakarta.persistence.OneToMany;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import java.util.List;

    @Entity
    @Table(name = Usuario.TABLE_NAME)
    public class Usuario {

        public static final String TABLE_NAME = "usuarios";
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nombreUsuario", unique = true, nullable = false)
        private String nombreUsuario;

        @Column(name = "password", nullable = false)
        private String password;

        @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<Transacciones> transacciones;

        public Usuario() {
        }

        public Usuario(Long id, List<Transacciones> transacciones, String password, String nombreUsuario) {
            this.id = id;
            this.transacciones = transacciones;
            this.password = password;
            this.nombreUsuario = nombreUsuario;
        }

        public Long getId() {
            return id;
        }       

        public void setId(Long id) {
            this.id = id;
        }

        public List<Transacciones> getTransacciones() {
            return transacciones;
        }

        public void setTransacciones(List<Transacciones> transacciones) {
            this.transacciones = transacciones;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }
    }