package oracle;

import java.io.Serializable;

public class CalificacionPK implements Serializable {
    private Long articuloCodigo;
    private String vendedorRut;

    public CalificacionPK() {
    }

    public CalificacionPK(Long articuloCodigo, String vendedorRut) {
        this.articuloCodigo = articuloCodigo;
        this.vendedorRut = vendedorRut;
    }

    public boolean equals(Object other) {
        if (other instanceof CalificacionPK) {
            final CalificacionPK otherCalificacionPK = (CalificacionPK) other;
            final boolean areEqual =
                (otherCalificacionPK.articuloCodigo.equals(articuloCodigo) && otherCalificacionPK.vendedorRut.equals(vendedorRut));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    Long getArticuloCodigo() {
        return articuloCodigo;
    }

    void setArticuloCodigo(Long articuloCodigo) {
        this.articuloCodigo = articuloCodigo;
    }

    String getVendedorRut() {
        return vendedorRut;
    }

    void setVendedorRut(String vendedorRut) {
        this.vendedorRut = vendedorRut;
    }
}
