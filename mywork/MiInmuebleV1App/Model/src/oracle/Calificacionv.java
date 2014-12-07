package oracle;

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
  @NamedQuery(name = "Calificacionv.findAll", query = "select o from Calificacionv o")
})
@IdClass(CalificacionvPK.class)
public class Calificacionv implements Serializable {
    @Column(nullable = false)
    private Float calificacion;
    @Id
    @Column(name="VENDEDOR_RUT", nullable = false, length = 30, insertable = false, updatable = false)
    private String vendedorRut;
    @Id
    @Column(name="VENDEDOR_RUT1", nullable = false, length = 30, insertable = false, updatable = false)
    private String vendedorRut1;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT1")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor1;

    public Calificacionv() {
    }

    public Calificacionv(Float calificacion, Vendedor vendedor1,
                         Vendedor vendedor) {
        this.calificacion = calificacion;
        this.vendedor1 = vendedor1;
        this.vendedor = vendedor;
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

    public String getVendedorRut1() {
        return vendedorRut1;
    }

    public void setVendedorRut1(String vendedorRut1) {
        this.vendedorRut1 = vendedorRut1;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        if (vendedor != null) {
            this.vendedorRut1 = vendedor.getRut();
        }
    }

    public Vendedor getVendedor1() {
        return vendedor1;
    }

    public void setVendedor1(Vendedor vendedor1) {
        this.vendedor1 = vendedor1;
        if (vendedor1 != null) {
            this.vendedorRut = vendedor1.getRut();
        }
    }
}
