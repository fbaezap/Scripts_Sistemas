package oracle.entities;

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
  @NamedQuery(name = "Comentario.findAll", query = "select o from Comentario o")
})
public class Comentario implements Serializable {
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(nullable = false)
    private Timestamp fecha;
    @Column(nullable = false, length = 360)
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "ARTICULO_CODIGO")
    private Articulo articulo;

    public Comentario() {
    }

    public Comentario(Articulo articulo, Long codigo, Timestamp fecha,
                      String mensaje, Vendedor vendedor) {
        this.articulo = articulo;
        this.codigo = codigo;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.vendedor = vendedor;
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
