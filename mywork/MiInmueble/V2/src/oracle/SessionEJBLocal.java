package oracle;

import java.util.List;

import javax.ejb.Local;

import oracle.entities.Articulo;
import oracle.entities.Calificacion;
import oracle.entities.Calificacionv;
import oracle.entities.Comentario;
import oracle.entities.Comuna;
import oracle.entities.Conectado;
import oracle.entities.Consulta;
import oracle.entities.Corredor;
import oracle.entities.Empresacorredora;
import oracle.entities.Fotos;
import oracle.entities.Inmobiliaria;
import oracle.entities.Membresia;
import oracle.entities.Persona;
import oracle.entities.Photos;
import oracle.entities.Provincia;
import oracle.entities.Region;
import oracle.entities.Vendedor;

@Local
public interface SessionEJBLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Provincia persistProvincia(Provincia provincia);

    Provincia mergeProvincia(Provincia provincia);

    void removeProvincia(Provincia provincia);

    List<Provincia> getProvinciaFindAll();

    Photos persistPhotos(Photos photos);

    Photos mergePhotos(Photos photos);

    void removePhotos(Photos photos);

    List<Photos> getPhotosFindAll();

    Persona persistPersona(Persona persona);

    Persona mergePersona(Persona persona);

    void removePersona(Persona persona);

    List<Persona> getPersonaFindAll();

    Region persistRegion(Region region);

    Region mergeRegion(Region region);

    void removeRegion(Region region);

    List<Region> getRegionFindAll();

    Conectado persistConectado(Conectado conectado);

    Conectado mergeConectado(Conectado conectado);

    void removeConectado(Conectado conectado);

    List<Conectado> getConectadoFindAll();

    Comentario persistComentario(Comentario comentario);

    Comentario mergeComentario(Comentario comentario);

    void removeComentario(Comentario comentario);

    List<Comentario> getComentarioFindAll();

    Consulta persistConsulta(Consulta consulta);

    Consulta mergeConsulta(Consulta consulta);

    void removeConsulta(Consulta consulta);

    List<Consulta> getConsultaFindAll();

    Empresacorredora persistEmpresacorredora(Empresacorredora empresacorredora);

    Empresacorredora mergeEmpresacorredora(Empresacorredora empresacorredora);

    void removeEmpresacorredora(Empresacorredora empresacorredora);

    List<Empresacorredora> getEmpresacorredoraFindAll();

    Calificacionv persistCalificacionv(Calificacionv calificacionv);

    Calificacionv mergeCalificacionv(Calificacionv calificacionv);

    void removeCalificacionv(Calificacionv calificacionv);

    List<Calificacionv> getCalificacionvFindAll();

    Corredor persistCorredor(Corredor corredor);

    Corredor mergeCorredor(Corredor corredor);

    void removeCorredor(Corredor corredor);

    List<Corredor> getCorredorFindAll();

    Vendedor persistVendedor(Vendedor vendedor);

    Vendedor mergeVendedor(Vendedor vendedor);

    void removeVendedor(Vendedor vendedor);

    List<Vendedor> getVendedorFindAll();

    Inmobiliaria persistInmobiliaria(Inmobiliaria inmobiliaria);

    Inmobiliaria mergeInmobiliaria(Inmobiliaria inmobiliaria);

    void removeInmobiliaria(Inmobiliaria inmobiliaria);

    List<Inmobiliaria> getInmobiliariaFindAll();

    List<Inmobiliaria> getInmobiliariaFindRut(Object p_rut);

    Membresia persistMembresia(Membresia membresia);

    Membresia mergeMembresia(Membresia membresia);

    void removeMembresia(Membresia membresia);

    List<Membresia> getMembresiaFindAll();

    Calificacion persistCalificacion(Calificacion calificacion);

    Calificacion mergeCalificacion(Calificacion calificacion);

    void removeCalificacion(Calificacion calificacion);

    List<Calificacion> getCalificacionFindAll();

    Fotos persistFotos(Fotos fotos);

    Fotos mergeFotos(Fotos fotos);

    void removeFotos(Fotos fotos);

    List<Fotos> getFotosFindAll();

    Comuna persistComuna(Comuna comuna);

    Comuna mergeComuna(Comuna comuna);

    void removeComuna(Comuna comuna);

    List<Comuna> getComunaFindAll();

    Articulo persistArticulo(Articulo articulo);

    Articulo mergeArticulo(Articulo articulo);

    void removeArticulo(Articulo articulo);

    List<Articulo> getArticuloFindAll();
}
