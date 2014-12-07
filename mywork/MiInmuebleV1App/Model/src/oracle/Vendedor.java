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
  @NamedQuery(name = "Vendedor.findAll", query = "select o from Vendedor o")
})
public class Vendedor implements Serializable {
    private Float calificacion;
    @Column(nullable = false, length = 360)
    private String contraseña;
    @Column(nullable = false, length = 360)
    private String correo;
    @Id
    @Column(nullable = false, length = 30)
    private String rut;
    @Column(length = 360)
    private String telefono;
    @Column(nullable = false, length = 20)
    private String tipo;
    @Column(nullable = false, length = 360)
    private String usuario;
    @OneToMany(mappedBy = "vendedor")
    private List<Corredor> corredorList;
    @ManyToOne
    @JoinColumn(name = "MEMBRESIA_CODIGO")
    private Membresia membresia;
    @OneToMany(mappedBy = "vendedor")
    private List<Comentario> comentarioList;
    @OneToMany(mappedBy = "vendedor")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "vendedor")
    private List<Inmobiliaria> inmobiliariaList;
    @OneToMany(mappedBy = "vendedor")
    private List<Articulo> articuloList;
    @OneToMany(mappedBy = "vendedor")
    private List<Calificacionv> calificacionvList;
    @OneToMany(mappedBy = "vendedor1")
    private List<Calificacionv> calificacionvList1;
    @OneToMany(mappedBy = "vendedor")
    private List<Calificacion> calificacionList;
    @OneToMany(mappedBy = "vendedor")
    private List<Conectado> conectadoList;

    public Vendedor() {
    }

    public Vendedor(Float calificacion, String contraseña, String correo,
                    Membresia membresia, String rut, String telefono,
                    String tipo, String usuario) {
        this.calificacion = calificacion;
        this.contraseña = contraseña;
        this.correo = correo;
        this.membresia = membresia;
        this.rut = rut;
        this.telefono = telefono;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Corredor> getCorredorList() {
        return corredorList;
    }

    public void setCorredorList(List<Corredor> corredorList) {
        this.corredorList = corredorList;
    }

    public Corredor addCorredor(Corredor corredor) {
        getCorredorList().add(corredor);
        corredor.setVendedor(this);
        return corredor;
    }

    public Corredor removeCorredor(Corredor corredor) {
        getCorredorList().remove(corredor);
        corredor.setVendedor(null);
        return corredor;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public Comentario addComentario(Comentario comentario) {
        getComentarioList().add(comentario);
        comentario.setVendedor(this);
        return comentario;
    }

    public Comentario removeComentario(Comentario comentario) {
        getComentarioList().remove(comentario);
        comentario.setVendedor(null);
        return comentario;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public Persona addPersona(Persona persona) {
        getPersonaList().add(persona);
        persona.setVendedor(this);
        return persona;
    }

    public Persona removePersona(Persona persona) {
        getPersonaList().remove(persona);
        persona.setVendedor(null);
        return persona;
    }

    public List<Inmobiliaria> getInmobiliariaList() {
        return inmobiliariaList;
    }

    public void setInmobiliariaList(List<Inmobiliaria> inmobiliariaList) {
        this.inmobiliariaList = inmobiliariaList;
    }

    public Inmobiliaria addInmobiliaria(Inmobiliaria inmobiliaria) {
        getInmobiliariaList().add(inmobiliaria);
        inmobiliaria.setVendedor(this);
        return inmobiliaria;
    }

    public Inmobiliaria removeInmobiliaria(Inmobiliaria inmobiliaria) {
        getInmobiliariaList().remove(inmobiliaria);
        inmobiliaria.setVendedor(null);
        return inmobiliaria;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    public Articulo addArticulo(Articulo articulo) {
        getArticuloList().add(articulo);
        articulo.setVendedor(this);
        return articulo;
    }

    public Articulo removeArticulo(Articulo articulo) {
        getArticuloList().remove(articulo);
        articulo.setVendedor(null);
        return articulo;
    }

    public List<Calificacionv> getCalificacionvList() {
        return calificacionvList;
    }

    public void setCalificacionvList(List<Calificacionv> calificacionvList) {
        this.calificacionvList = calificacionvList;
    }

    public Calificacionv addCalificacionv(Calificacionv calificacionv) {
        getCalificacionvList().add(calificacionv);
        calificacionv.setVendedor(this);
        return calificacionv;
    }

    public Calificacionv removeCalificacionv(Calificacionv calificacionv) {
        getCalificacionvList().remove(calificacionv);
        calificacionv.setVendedor(null);
        return calificacionv;
    }

    public List<Calificacionv> getCalificacionvList1() {
        return calificacionvList1;
    }

    public void setCalificacionvList1(List<Calificacionv> calificacionvList1) {
        this.calificacionvList1 = calificacionvList1;
    }

    public Calificacionv addCalificacionv1(Calificacionv calificacionv) {
        getCalificacionvList1().add(calificacionv);
        calificacionv.setVendedor1(this);
        return calificacionv;
    }

    public Calificacionv removeCalificacionv1(Calificacionv calificacionv) {
        getCalificacionvList1().remove(calificacionv);
        calificacionv.setVendedor1(null);
        return calificacionv;
    }

    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    public Calificacion addCalificacion(Calificacion calificacion) {
        getCalificacionList().add(calificacion);
        calificacion.setVendedor(this);
        return calificacion;
    }

    public Calificacion removeCalificacion(Calificacion calificacion) {
        getCalificacionList().remove(calificacion);
        calificacion.setVendedor(null);
        return calificacion;
    }

    public List<Conectado> getConectadoList() {
        return conectadoList;
    }

    public void setConectadoList(List<Conectado> conectadoList) {
        this.conectadoList = conectadoList;
    }

    public Conectado addConectado(Conectado conectado) {
        getConectadoList().add(conectado);
        conectado.setVendedor(this);
        return conectado;
    }

    public Conectado removeConectado(Conectado conectado) {
        getConectadoList().remove(conectado);
        conectado.setVendedor(null);
        return conectado;
    }
}
