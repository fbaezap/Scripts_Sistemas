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

    public Fotos(Articulo articulo, Long id) {
        this.articulo = articulo;
        this.id = id;
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
