package oracle.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Fotos.findAll", query = "select o from Fotos o")
})
public class Fotos implements Serializable {
    @Column(nullable = false, length = 5)
    private String formato;
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private byte[] imagen;
    @ManyToOne
    @JoinColumn(name = "ARTICULO_CODIGO")
    private Articulo articulo;

    public Fotos() {
    }

    public Fotos(Articulo articulo, String formato, Long id) {
        this.articulo = articulo;
        this.formato = formato;
        this.id = id;
    }


    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
