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
  @NamedQuery(name = "Provincia.findAll", query = "select o from Provincia o")
})
public class Provincia implements Serializable {
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(nullable = false, length = 360)
    private String nombre;
    @OneToMany(mappedBy = "provincia")
    private List<Comuna> comunaList;
    @ManyToOne
    @JoinColumn(name = "REGION_CODIGO")
    private Region region;

    public Provincia() {
    }

    public Provincia(Long codigo, String nombre, Region region) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.region = region;
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


    public List<Comuna> getComunaList() {
        return comunaList;
    }

    public void setComunaList(List<Comuna> comunaList) {
        this.comunaList = comunaList;
    }

    public Comuna addComuna(Comuna comuna) {
        getComunaList().add(comuna);
        comuna.setProvincia(this);
        return comuna;
    }

    public Comuna removeComuna(Comuna comuna) {
        getComunaList().remove(comuna);
        comuna.setProvincia(null);
        return comuna;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
