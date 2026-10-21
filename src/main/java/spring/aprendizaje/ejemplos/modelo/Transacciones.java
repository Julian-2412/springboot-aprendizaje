package spring.aprendizaje.ejemplos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = Transacciones.TABLE_NAME)

public class Transacciones {

    public static final String TABLE_NAME = "transacciones";
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "monto")
    private Double monto;
    
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private Long fecha;

    @Column(name = "estado")
    private String estado;

    @Column(name = "ultimaModificacion")
    private Long ultimaModificacion;
    
    public Transacciones() {
    }

    public Transacciones(String id, Usuario usuario, String titulo, Double monto, String descripcion, Long fecha, String estado, Long ultimaModificacion) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getFecha() {
        return fecha;
    }
    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUltimaModificacion() {
        return ultimaModificacion;
    }
    public void setUltimaModificacion(Long ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
}
