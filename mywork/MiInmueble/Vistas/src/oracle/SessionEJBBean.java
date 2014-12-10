package oracle;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import oracle.entities.Articulo;
import oracle.entities.Calificacion;
import oracle.entities.CalificacionPK;
import oracle.entities.Calificacionv;
import oracle.entities.CalificacionvPK;
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
import oracle.entities.Provincia;
import oracle.entities.Region;
import oracle.entities.Vendedor;

@Stateless(name = "SessionEJB", mappedName = "MiInmueble-Vistas-SessionEJB")
@Remote
@Local
public class SessionEJBBean implements SessionEJB, SessionEJBLocal {
    @PersistenceContext(unitName="Vistas")
    private EntityManager em;

    public SessionEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Membresia persistMembresia(Membresia membresia) {
        em.persist(membresia);
        return membresia;
    }

    public Membresia mergeMembresia(Membresia membresia) {
        return em.merge(membresia);
    }

    public void removeMembresia(Membresia membresia) {
        membresia = em.find(Membresia.class, membresia.getCodigo());
        em.remove(membresia);
    }

    /** <code>select o from Membresia o</code> */
    public List<Membresia> getMembresiaFindAll() {
        return em.createNamedQuery("Membresia.findAll").getResultList();
    }

    public Provincia persistProvincia(Provincia provincia) {
        em.persist(provincia);
        return provincia;
    }

    public Provincia mergeProvincia(Provincia provincia) {
        return em.merge(provincia);
    }

    public void removeProvincia(Provincia provincia) {
        provincia = em.find(Provincia.class, provincia.getCodigo());
        em.remove(provincia);
    }

    /** <code>select o from Provincia o</code> */
    public List<Provincia> getProvinciaFindAll() {
        return em.createNamedQuery("Provincia.findAll").getResultList();
    }

    public Consulta persistConsulta(Consulta consulta) {
        em.persist(consulta);
        return consulta;
    }

    public Consulta mergeConsulta(Consulta consulta) {
        return em.merge(consulta);
    }

    public void removeConsulta(Consulta consulta) {
        consulta = em.find(Consulta.class, consulta.getCodigo());
        em.remove(consulta);
    }

    /** <code>select o from Consulta o</code> */
    public List<Consulta> getConsultaFindAll() {
        return em.createNamedQuery("Consulta.findAll").getResultList();
    }

    public Empresacorredora persistEmpresacorredora(Empresacorredora empresacorredora) {
        em.persist(empresacorredora);
        return empresacorredora;
    }

    public Empresacorredora mergeEmpresacorredora(Empresacorredora empresacorredora) {
        return em.merge(empresacorredora);
    }

    public void removeEmpresacorredora(Empresacorredora empresacorredora) {
        empresacorredora = em.find(Empresacorredora.class, empresacorredora.getRut());
        em.remove(empresacorredora);
    }

    /** <code>select o from Empresacorredora o</code> */
    public List<Empresacorredora> getEmpresacorredoraFindAll() {
        return em.createNamedQuery("Empresacorredora.findAll").getResultList();
    }

    public Persona persistPersona(Persona persona) {
        em.persist(persona);
        return persona;
    }

    public Persona mergePersona(Persona persona) {
        return em.merge(persona);
    }

    public void removePersona(Persona persona) {
        persona = em.find(Persona.class, persona.getVendedorRut());
        em.remove(persona);
    }

    /** <code>select o from Persona o</code> */
    public List<Persona> getPersonaFindAll() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    public Calificacion persistCalificacion(Calificacion calificacion) {
        em.persist(calificacion);
        return calificacion;
    }

    public Calificacion mergeCalificacion(Calificacion calificacion) {
        return em.merge(calificacion);
    }

    public void removeCalificacion(Calificacion calificacion) {
        calificacion = em.find(Calificacion.class, new CalificacionPK(calificacion.getArticuloCodigo(), calificacion.getVendedorRut()));
        em.remove(calificacion);
    }

    /** <code>select o from Calificacion o</code> */
    public List<Calificacion> getCalificacionFindAll() {
        return em.createNamedQuery("Calificacion.findAll").getResultList();
    }

    public Inmobiliaria persistInmobiliaria(Inmobiliaria inmobiliaria) {
        em.persist(inmobiliaria);
        return inmobiliaria;
    }

    public Inmobiliaria mergeInmobiliaria(Inmobiliaria inmobiliaria) {
        return em.merge(inmobiliaria);
    }

    public void removeInmobiliaria(Inmobiliaria inmobiliaria) {
        inmobiliaria = em.find(Inmobiliaria.class, inmobiliaria.getVendedorRut());
        em.remove(inmobiliaria);
    }

    /** <code>select o from Inmobiliaria o</code> */
    public List<Inmobiliaria> getInmobiliariaFindAll() {
        return em.createNamedQuery("Inmobiliaria.findAll").getResultList();
    }

    /** <code>select o from Inmobiliaria o where o.vendedorRut = :p_rut</code> */
    public List<Inmobiliaria> getInmobiliariaFindRut(Object p_rut) {
        return em.createNamedQuery("Inmobiliaria.findRut").setParameter("p_rut", p_rut).getResultList();
    }

    public Corredor persistCorredor(Corredor corredor) {
        em.persist(corredor);
        return corredor;
    }

    public Corredor mergeCorredor(Corredor corredor) {
        return em.merge(corredor);
    }

    public void removeCorredor(Corredor corredor) {
        corredor = em.find(Corredor.class, corredor.getVendedorRut());
        em.remove(corredor);
    }

    /** <code>select o from Corredor o</code> */
    public List<Corredor> getCorredorFindAll() {
        return em.createNamedQuery("Corredor.findAll").getResultList();
    }

    public Vendedor persistVendedor(Vendedor vendedor) {
        em.persist(vendedor);
        return vendedor;
    }

    public Vendedor mergeVendedor(Vendedor vendedor) {
        return em.merge(vendedor);
    }

    public void removeVendedor(Vendedor vendedor) {
        vendedor = em.find(Vendedor.class, vendedor.getRut());
        em.remove(vendedor);
    }

    /** <code>select o from Vendedor o</code> */
    public List<Vendedor> getVendedorFindAll() {
        return em.createNamedQuery("Vendedor.findAll").getResultList();
    }

    public Conectado persistConectado(Conectado conectado) {
        em.persist(conectado);
        return conectado;
    }

    public Conectado mergeConectado(Conectado conectado) {
        return em.merge(conectado);
    }

    public void removeConectado(Conectado conectado) {
        conectado = em.find(Conectado.class, conectado.getVendedorRut());
        em.remove(conectado);
    }

    /** <code>select o from Conectado o</code> */
    public List<Conectado> getConectadoFindAll() {
        return em.createNamedQuery("Conectado.findAll").getResultList();
    }

    public Comuna persistComuna(Comuna comuna) {
        em.persist(comuna);
        return comuna;
    }

    public Comuna mergeComuna(Comuna comuna) {
        return em.merge(comuna);
    }

    public void removeComuna(Comuna comuna) {
        comuna = em.find(Comuna.class, comuna.getCodigo());
        em.remove(comuna);
    }

    /** <code>select o from Comuna o</code> */
    public List<Comuna> getComunaFindAll() {
        return em.createNamedQuery("Comuna.findAll").getResultList();
    }

    public Region persistRegion(Region region) {
        em.persist(region);
        return region;
    }

    public Region mergeRegion(Region region) {
        return em.merge(region);
    }

    public void removeRegion(Region region) {
        region = em.find(Region.class, region.getCodigo());
        em.remove(region);
    }

    /** <code>select o from Region o</code> */
    public List<Region> getRegionFindAll() {
        return em.createNamedQuery("Region.findAll").getResultList();
    }

    public Articulo persistArticulo(Articulo articulo) {
        em.persist(articulo);
        return articulo;
    }

    public Articulo mergeArticulo(Articulo articulo) {
        return em.merge(articulo);
    }

    public void removeArticulo(Articulo articulo) {
        articulo = em.find(Articulo.class, articulo.getCodigo());
        em.remove(articulo);
    }

    /** <code>select o from Articulo o</code> */
    public List<Articulo> getArticuloFindAll() {
        return em.createNamedQuery("Articulo.findAll").getResultList();
    }

    /** <code>select o from Articulo o where o.destacada=:p_dest</code> */
    public List<Articulo> getArticuloFindDestacada(Object p_dest) {
        return em.createNamedQuery("Articulo.findDestacada").setParameter("p_dest", p_dest).getResultList();
    }

    public Fotos persistFotos(Fotos fotos) {
        em.persist(fotos);
        return fotos;
    }

    public Fotos mergeFotos(Fotos fotos) {
        return em.merge(fotos);
    }

    public void removeFotos(Fotos fotos) {
        fotos = em.find(Fotos.class, fotos.getId());
        em.remove(fotos);
    }

    /** <code>select o from Fotos o</code> */
    public List<Fotos> getFotosFindAll() {
        return em.createNamedQuery("Fotos.findAll").getResultList();
    }

    public Comentario persistComentario(Comentario comentario) {
        em.persist(comentario);
        return comentario;
    }

    public Comentario mergeComentario(Comentario comentario) {
        return em.merge(comentario);
    }

    public void removeComentario(Comentario comentario) {
        comentario = em.find(Comentario.class, comentario.getCodigo());
        em.remove(comentario);
    }

    /** <code>select o from Comentario o</code> */
    public List<Comentario> getComentarioFindAll() {
        return em.createNamedQuery("Comentario.findAll").getResultList();
    }

    public Calificacionv persistCalificacionv(Calificacionv calificacionv) {
        em.persist(calificacionv);
        return calificacionv;
    }

    public Calificacionv mergeCalificacionv(Calificacionv calificacionv) {
        return em.merge(calificacionv);
    }

    public void removeCalificacionv(Calificacionv calificacionv) {
        calificacionv = em.find(Calificacionv.class, new CalificacionvPK(calificacionv.getVendedorRut(), calificacionv.getVendedorRut1()));
        em.remove(calificacionv);
    }

    /** <code>select o from Calificacionv o</code> */
    public List<Calificacionv> getCalificacionvFindAll() {
        return em.createNamedQuery("Calificacionv.findAll").getResultList();
    }
}
