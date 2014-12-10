package oracle.entities;

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
  @NamedQuery(name = "Persona.findAll", query = "select o from Persona o")
})
public class Persona implements Serializable {
    @Column(nullable = false, length = 360)
    private String apellidos;
    @Column(nullable = false, length = 10)
    private String genero;
    @Column(nullable = false, length = 360)
    private String nombre;
    @Id
    @Column(name="VENDEDOR_RUT", nullable = false, length = 360, insertable = false, updatable = false)
    private String vendedorRut;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor;

    public Persona() {
    }

    public Persona(String apellidos, String genero, String nombre,
                   Vendedor vendedor) {
        this.apellidos = apellidos;
        this.genero = genero;
        this.nombre = nombre;
        this.vendedor = vendedor;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
