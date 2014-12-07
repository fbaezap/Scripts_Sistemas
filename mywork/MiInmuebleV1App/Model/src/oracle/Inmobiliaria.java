package oracle;

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
  @NamedQuery(name = "Inmobiliaria.findAll", query = "select o from Inmobiliaria o")
})
public class Inmobiliaria implements Serializable {
    @Column(name="DOMICILIO_COMERCIAL", length = 360)
    private String domicilioComercial;
    @Column(name="DOMICILIO_LEGAL", length = 360)
    private String domicilioLegal;
    private byte[] logo;
    @Column(nullable = false, length = 360)
    private String nombre;
    @Column(length = 360)
    private String paginaweb;
    @Column(name="TIPO_SOCIEDAD", length = 360)
    private String tipoSociedad;
    @Id
    @Column(name="VENDEDOR_RUT", nullable = false, length = 30, insertable = false, updatable = false)
    private String vendedorRut;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor;

    public Inmobiliaria() {
    }

    public Inmobiliaria(String domicilioComercial, String domicilioLegal,
                        String nombre, String paginaweb, String tipoSociedad,
                        Vendedor vendedor) {
        this.domicilioComercial = domicilioComercial;
        this.domicilioLegal = domicilioLegal;
        this.nombre = nombre;
        this.paginaweb = paginaweb;
        this.tipoSociedad = tipoSociedad;
        this.vendedor = vendedor;
    }

    public String getDomicilioComercial() {
        return domicilioComercial;
    }

    public void setDomicilioComercial(String domicilioComercial) {
        this.domicilioComercial = domicilioComercial;
    }

    public String getDomicilioLegal() {
        return domicilioLegal;
    }

    public void setDomicilioLegal(String domicilioLegal) {
        this.domicilioLegal = domicilioLegal;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaginaweb() {
        return paginaweb;
    }

    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }

    public String getTipoSociedad() {
        return tipoSociedad;
    }

    public void setTipoSociedad(String tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public String getVendedorRut() {
        return vendedorRut;
    }

    public void setVendedorRut(String vendedorRut) {
        this.vendedorRut = vendedorRut;
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
