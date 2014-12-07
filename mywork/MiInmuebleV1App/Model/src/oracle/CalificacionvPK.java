package oracle;

import java.io.Serializable;

public class CalificacionvPK implements Serializable {
    private String vendedorRut;
    private String vendedorRut1;

    public CalificacionvPK() {
    }

    public CalificacionvPK(String vendedorRut, String vendedorRut1) {
        this.vendedorRut = vendedorRut;
        this.vendedorRut1 = vendedorRut1;
    }

    public boolean equals(Object other) {
        if (other instanceof CalificacionvPK) {
            final CalificacionvPK otherCalificacionvPK = (CalificacionvPK) other;
            final boolean areEqual =
                (otherCalificacionvPK.vendedorRut.equals(vendedorRut) && otherCalificacionvPK.vendedorRut1.equals(vendedorRut1));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    String getVendedorRut() {
        return vendedorRut;
    }

    void setVendedorRut(String vendedorRut) {
        this.vendedorRut = vendedorRut;
    }

    String getVendedorRut1() {
        return vendedorRut1;
    }

    void setVendedorRut1(String vendedorRut1) {
        this.vendedorRut1 = vendedorRut1;
    }
}
