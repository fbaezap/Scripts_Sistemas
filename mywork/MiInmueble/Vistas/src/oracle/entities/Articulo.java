package oracle.entities;

import java.io.Serializable;

import java.sql.Timestamp;

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
  @NamedQuery(name = "Articulo.findAll", query = "select o from Articulo o")
  ,
  @NamedQuery(name = "Articulo.findDestacada", query = "select o from Articulo o where o.destacada=:p_dest")
})
public class Articulo implements Serializable {
    private Long baños;
    private Long calificacion;
    @Column(length = 360)
    private String ciudad;
    @Id
    @Column(nullable = false)
    private Long codigo;
    @Column(name="CODI_INTERNO", length = 360)
    private String codiInterno;
    @Column(nullable = false, length = 360)
    private String descripcion;
    private String destacada;
    @Column(nullable = false, length = 360)
    private String direccion;
    @Column(nullable = false, length = 100)
    private String estructura;
    private Timestamp fecha;
    @Column(nullable = false, length = 360)
    private String monto;
    private Long piezas;
    private Long pisos;
    @Column(length = 360)
    private String superficie;
    private Long superficieconstruida;
    @Column(length = 10)
    private String tipo;
    @Column(nullable = false, length = 360)
    private String titulo;
    @OneToMany(mappedBy = "articulo")
    private List<Fotos> fotosList;
    @OneToMany(mappedBy = "articulo")
    private List<Consulta> consultaList;
    @ManyToOne
    @JoinColumn(name = "COMUNA_CODIGO")
    private Comuna comuna;
    @OneToMany(mappedBy = "articulo")
    private List<Calificacion> calificacionList;
    @ManyToOne
    @JoinColumn(name = "VENDEDOR_RUT")
    private Vendedor vendedor;
    @OneToMany(mappedBy = "articulo")
    private List<Comentario> comentarioList;

    public Articulo() {
    }

    public Articulo(Long baños, Long calificacion, String ciudad,
                    String codiInterno, Long codigo, Comuna comuna,
                    String descripcion, String destacada, String direccion,
                    String estructura, Timestamp fecha, String monto,
                    Long piezas, Long pisos, String superficie,
                    Long superficieconstruida, String tipo, String titulo,
                    Vendedor vendedor) {
        this.baños = baños;
        this.calificacion = calificacion;
        this.ciudad = ciudad;
        this.codiInterno = codiInterno;
        this.codigo = codigo;
        this.comuna = comuna;
        this.descripcion = descripcion;
        this.destacada = destacada;
        this.direccion = direccion;
        this.estructura = estructura;
        this.fecha = fecha;
        this.monto = monto;
        this.piezas = piezas;
        this.pisos = pisos;
        this.superficie = superficie;
        this.superficieconstruida = superficieconstruida;
        this.tipo = tipo;
        this.titulo = titulo;
        this.vendedor = vendedor;
    }

    public Long getBaños() {
        return baños;
    }

    public void setBaños(Long baños) {
        this.baños = baños;
    }

    public Long getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Long calificacion) {
        this.calificacion = calificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCodiInterno() {
        return codiInterno;
    }

    public void setCodiInterno(String codiInterno) {
        this.codiInterno = codiInterno;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestacada() {
        return destacada;
    }

    public void setDestacada(String destacada) {
        this.destacada = destacada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Long getPiezas() {
        return piezas;
    }

    public void setPiezas(Long piezas) {
        this.piezas = piezas;
    }

    public Long getPisos() {
        return pisos;
    }

    public void setPisos(Long pisos) {
        this.pisos = pisos;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public Long getSuperficieconstruida() {
        return superficieconstruida;
    }

    public void setSuperficieconstruida(Long superficieconstruida) {
        this.superficieconstruida = superficieconstruida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public List<Fotos> getFotosList() {
        return fotosList;
    }

    public void setFotosList(List<Fotos> fotosList) {
        this.fotosList = fotosList;
    }

    public Fotos addFotos(Fotos fotos) {
        getFotosList().add(fotos);
        fotos.setArticulo(this);
        return fotos;
    }

    public Fotos removeFotos(Fotos fotos) {
        getFotosList().remove(fotos);
        fotos.setArticulo(null);
        return fotos;
    }

    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    public Consulta addConsulta(Consulta consulta) {
        getConsultaList().add(consulta);
        consulta.setArticulo(this);
        return consulta;
    }

    public Consulta removeConsulta(Consulta consulta) {
        getConsultaList().remove(consulta);
        consulta.setArticulo(null);
        return consulta;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    public Calificacion addCalificacion(Calificacion calificacion) {
        getCalificacionList().add(calificacion);
        calificacion.setArticulo(this);
        return calificacion;
    }

    public Calificacion removeCalificacion(Calificacion calificacion) {
        getCalificacionList().remove(calificacion);
        calificacion.setArticulo(null);
        return calificacion;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public Comentario addComentario(Comentario comentario) {
        getComentarioList().add(comentario);
        comentario.setArticulo(this);
        return comentario;
    }

    public Comentario removeComentario(Comentario comentario) {
        getComentarioList().remove(comentario);
        comentario.setArticulo(null);
        return comentario;
    }
}
