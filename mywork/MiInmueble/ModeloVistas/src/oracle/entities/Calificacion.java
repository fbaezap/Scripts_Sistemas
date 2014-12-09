package oracle.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Calificacion.findAll", query = "select o from Calificacion o")
})
@IdClass(CalificacionPK.class)
public class Calificacion implements Serializable {
    @Id
    @Column(name="ARTICULO_CODIGO", nullable = false, insertable = false,
            updatable = false)
    private Long articuloCodigo;
    @Column(nullable = false)
    private Float calificacion;
    @Id
    @Column(name="VENDEDOR_RUT", nullable = false, length = 360, insertable = false, updatable = false)
    private String vendedorRut;
    @ManyToOne
    @JoinColumn(name = "ARTICULO_CODIGO")
    private Articulo articulo;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor;

    public Calificacion() {
    }

    public Calificacion(Articulo articulo, Float calificacion,
                        Vendedor vendedor) {
        this.articulo = articulo;
        this.calificacion = calificacion;
        this.vendedor = vendedor;
    }

    public Long getArticuloCodigo() {
        return articuloCodigo;
    }

    public void setArticuloCodigo(Long articuloCodigo) {
        this.articuloCodigo = articuloCodigo;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public String getVendedorRut() {
        return vendedorRut;
    }

    public void setVendedorRut(String vendedorRut) {
        this.vendedorRut = vendedorRut;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
        if (articulo != null) {
            this.articuloCodigo = articulo.getCodigo();
        }
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        if (vendedor != null) {
            this.vendedorRut = vendedor.getRut();
        }
    }
}
