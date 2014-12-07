package oracle;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
  @NamedQuery(name = "Comuna.findAll", query = "select o from Comuna o")
})
public class Comuna implements Serializable {
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(nullable = false, length = 360)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "PROVINCIA_CODIGO")
    private Provincia provincia;
    @OneToMany(mappedBy = "comuna")
    private List<Articulo> articuloList;

    public Comuna() {
    }

    public Comuna(Long codigo, String nombre, Provincia provincia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public Articulo addArticulo(Articulo articulo) {
        getArticuloList().add(articulo);
        articulo.setComuna(this);
        return articulo;
    }

    public Articulo removeArticulo(Articulo articulo) {
        getArticuloList().remove(articulo);
        articulo.setComuna(null);
        return articulo;
    }
}
