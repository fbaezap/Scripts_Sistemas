package oracle.entities;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Conectado.findAll", query = "select o from Conectado o")
})
public class Conectado implements Serializable {
    private Timestamp horainicio;
    @Column(length = 360)
    private String ip;
    @Id
    @Column(name="VENDEDOR_RUT", nullable = false, length = 360, insertable = false, updatable = false)
    private String vendedorRut;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor;

    public Conectado() {
    }

    public Conectado(Timestamp horainicio, String ip, Vendedor vendedor) {
        this.horainicio = horainicio;
        this.ip = ip;
        this.vendedor = vendedor;
    }

    public Timestamp getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Timestamp horainicio) {
        this.horainicio = horainicio;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
