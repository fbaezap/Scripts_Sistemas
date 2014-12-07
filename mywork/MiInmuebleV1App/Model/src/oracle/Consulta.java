package oracle;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Consulta.findAll", query = "select o from Consulta o")
})
public class Consulta implements Serializable {
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(name="CORREO_RESPUESTA", nullable = false, length = 360)
    private String correoRespuesta;
    private Timestamp hora;
    @Column(nullable = false, length = 360)
    private String mensaje;
    @Column(nullable = false, length = 360)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "ARTICULO_CODIGO")
    private Articulo articulo;

    public Consulta() {
    }

    public Consulta(Articulo articulo, Long codigo, String correoRespuesta,
                    Timestamp hora, String mensaje, String nombre) {
        this.articulo = articulo;
        this.codigo = codigo;
        this.correoRespuesta = correoRespuesta;
        this.hora = hora;
        this.mensaje = mensaje;
        this.nombre = nombre;
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCorreoRespuesta() {
        return correoRespuesta;
    }

    public void setCorreoRespuesta(String correoRespuesta) {
        this.correoRespuesta = correoRespuesta;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
