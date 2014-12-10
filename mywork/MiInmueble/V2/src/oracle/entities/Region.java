package oracle.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
  @NamedQuery(name = "Region.findAll", query = "select o from Region o")
})
public class Region implements Serializable {
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(nullable = false, length = 360)
    private String nombre;
    private Long posicion;
    @OneToMany(mappedBy = "region")
    private List<Provincia> provinciaList;

    public Region() {
    }

    public Region(Long codigo, String nombre, Long posicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.posicion = posicion;
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

    public Long getPosicion() {
        return posicion;
    }

    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    public List<Provincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public Provincia addProvincia(Provincia provincia) {
        getProvinciaList().add(provincia);
        provincia.setRegion(this);
        return provincia;
    }

    public Provincia removeProvincia(Provincia provincia) {
        getProvinciaList().remove(provincia);
        provincia.setRegion(null);
        return provincia;
    }
}
