package oracle;

import java.util.List;

import javax.ejb.Local;

@Local
public interface HRFacadeLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Conectado mergeConectado(Conectado conectado);

    void removeConectado(Conectado conectado);

    List<Conectado> getConectadoFindAll();

    Vendedor mergeVendedor(Vendedor vendedor);

    void removeVendedor(Vendedor vendedor);

    List<Vendedor> getVendedorFindAll();

    Comuna mergeComuna(Comuna comuna);

    void removeComuna(Comuna comuna);

    List<Comuna> getComunaFindAll();

    Articulo mergeArticulo(Articulo articulo);

    void removeArticulo(Articulo articulo);

    List<Articulo> getArticuloFindAll();

    Fotos mergeFotos(Fotos fotos);

    void removeFotos(Fotos fotos);

    List<Fotos> getFotosFindAll();

    Comentario persistComentario(Comentario comentario);

    Comentario mergeComentario(Comentario comentario);

    void removeComentario(Comentario comentario);

    List<Comentario> getComentarioFindAll();

    Corredor mergeCorredor(Corredor corredor);

    void removeCorredor(Corredor corredor);

    List<Corredor> getCorredorFindAll();

    Membresia mergeMembresia(Membresia membresia);

    void removeMembresia(Membresia membresia);

    List<Membresia> getMembresiaFindAll();

    Calificacion mergeCalificacion(Calificacion calificacion);

    void removeCalificacion(Calificacion calificacion);

    List<Calificacion> getCalificacionFindAll();

    Region mergeRegion(Region region);

    void removeRegion(Region region);

    List<Region> getRegionFindAll();

    Provincia mergeProvincia(Provincia provincia);

    void removeProvincia(Provincia provincia);

    List<Provincia> getProvinciaFindAll();

    Persona mergePersona(Persona persona);

    void removePersona(Persona persona);

    List<Persona> getPersonaFindAll();

    Empresacorredora mergeEmpresacorredora(Empresacorredora empresacorredora);

    void removeEmpresacorredora(Empresacorredora empresacorredora);

    List<Empresacorredora> getEmpresacorredoraFindAll();

    Inmobiliaria mergeInmobiliaria(Inmobiliaria inmobiliaria);

    void removeInmobiliaria(Inmobiliaria inmobiliaria);

    List<Inmobiliaria> getInmobiliariaFindAll();

    Calificacionv mergeCalificacionv(Calificacionv calificacionv);

    void removeCalificacionv(Calificacionv calificacionv);

    List<Calificacionv> getCalificacionvFindAll();

    Consulta mergeConsulta(Consulta consulta);

    void removeConsulta(Consulta consulta);

    List<Consulta> getConsultaFindAll();

    List<Articulo> getArticuloFindByDestacada(Object d);
}
