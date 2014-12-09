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
  @NamedQuery(name = "Empresacorredora.findAll", query = "select o from Empresacorredora o")
})
public class Empresacorredora implements Serializable {
    @Column(length = 360)
    private String correo;
    private byte[] logo;
    @Column(nullable = false, length = 360)
    private String nombre;
    @Column(length = 360)
    private String paginaweb;
    @Id
    @Column(nullable = false, length = 360)
    private String rut;
    @OneToMany(mappedBy = "empresacorredora")
    private List<Corredor> corredorList;

    public Empresacorredora() {
    }

    public Empresacorredora(String correo, String nombre, String paginaweb,
                            String rut) {
        this.correo = correo;
        this.nombre = nombre;
        this.paginaweb = paginaweb;
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public List<Corredor> getCorredorList() {
        return corredorList;
    }

    public void setCorredorList(List<Corredor> corredorList) {
        this.corredorList = corredorList;
    }

    public Corredor addCorredor(Corredor corredor) {
        getCorredorList().add(corredor);
        corredor.setEmpresacorredora(this);
        return corredor;
    }

    public Corredor removeCorredor(Corredor corredor) {
        getCorredorList().remove(corredor);
        corredor.setEmpresacorredora(null);
        return corredor;
    }
}
