package oracle;

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
  @NamedQuery(name = "Membresia.findAll", query = "select o from Membresia o")
})
public class Membresia implements Serializable {
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(nullable = false)
    private Long ndestacados;
    @Column(nullable = false)
    private Long nfotos;
    @Column(nullable = false, length = 360)
    private String nombre;
    @Column(nullable = false)
    private Long npublicaciones;
    @Column(nullable = false)
    private Long precio;
    @Column(length = 20)
    private String tipo;
    @OneToMany(mappedBy = "membresia")
    private List<Vendedor> vendedorList;

    public Membresia() {
    }

    public Membresia(Long codigo, Long ndestacados, Long nfotos, String nombre,
                     Long npublicaciones, Long precio, String tipo) {
        this.codigo = codigo;
        this.ndestacados = ndestacados;
        this.nfotos = nfotos;
        this.nombre = nombre;
        this.npublicaciones = npublicaciones;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getNdestacados() {
        return ndestacados;
    }

    public void setNdestacados(Long ndestacados) {
        this.ndestacados = ndestacados;
    }

    public Long getNfotos() {
        return nfotos;
    }

    public void setNfotos(Long nfotos) {
        this.nfotos = nfotos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNpublicaciones() {
        return npublicaciones;
    }

    public void setNpublicaciones(Long npublicaciones) {
        this.npublicaciones = npublicaciones;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Vendedor> getVendedorList() {
        return vendedorList;
    }

    public void setVendedorList(List<Vendedor> vendedorList) {
        this.vendedorList = vendedorList;
    }

    public Vendedor addVendedor(Vendedor vendedor) {
        getVendedorList().add(vendedor);
        vendedor.setMembresia(this);
        return vendedor;
    }

    public Vendedor removeVendedor(Vendedor vendedor) {
        getVendedorList().remove(vendedor);
        vendedor.setMembresia(null);
        return vendedor;
    }
}
