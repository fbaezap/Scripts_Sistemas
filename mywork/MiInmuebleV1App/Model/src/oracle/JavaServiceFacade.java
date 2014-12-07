package oracle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("outside");

    public JavaServiceFacade() {
    }

    public static void main(String [] args) {
        final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
        //  TODO:  Call methods on javaServiceFacade here...
        Articulo a = javaServiceFacade.getArticuloFindAll().get(0);
        System.out.println(a.getTitulo());
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = getEntityManager().createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    private Object _mergeEntity(Object entity) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.merge(entity);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    entity = null;
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return entity;
    }

    public Conectado mergeConectado(Conectado conectado) {
        return (Conectado)_mergeEntity(conectado);
    }

    public void removeConectado(Conectado conectado) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                conectado = em.find(Conectado.class, conectado.getVendedorRut());
                em.remove(conectado);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Conectado o</code> */
    public List<Conectado> getConectadoFindAll() {
        return getEntityManager().createNamedQuery("Conectado.findAll").getResultList();
    }

    public Vendedor mergeVendedor(Vendedor vendedor) {
        return (Vendedor)_mergeEntity(vendedor);
    }

    public void removeVendedor(Vendedor vendedor) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                vendedor = em.find(Vendedor.class, vendedor.getRut());
                em.remove(vendedor);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Vendedor o</code> */
    public List<Vendedor> getVendedorFindAll() {
        return getEntityManager().createNamedQuery("Vendedor.findAll").getResultList();
    }

    public Comuna mergeComuna(Comuna comuna) {
        return (Comuna)_mergeEntity(comuna);
    }

    public void removeComuna(Comuna comuna) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                comuna = em.find(Comuna.class, comuna.getCodigo());
                em.remove(comuna);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Comuna o</code> */
    public List<Comuna> getComunaFindAll() {
        return getEntityManager().createNamedQuery("Comuna.findAll").getResultList();
    }

    public Articulo mergeArticulo(Articulo articulo) {
        return (Articulo)_mergeEntity(articulo);
    }

    public void removeArticulo(Articulo articulo) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                articulo = em.find(Articulo.class, articulo.getCodigo());
                em.remove(articulo);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Articulo o</code> */
    public List<Articulo> getArticuloFindAll() {
        return getEntityManager().createNamedQuery("Articulo.findAll").getResultList();
    }

    public Fotos mergeFotos(Fotos fotos) {
        return (Fotos)_mergeEntity(fotos);
    }

    public void removeFotos(Fotos fotos) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                fotos = em.find(Fotos.class, fotos.getId());
                em.remove(fotos);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Fotos o</code> */
    public List<Fotos> getFotosFindAll() {
        return getEntityManager().createNamedQuery("Fotos.findAll").getResultList();
    }

    public Comentario mergeComentario(Comentario comentario) {
        return (Comentario)_mergeEntity(comentario);
    }

    public void removeComentario(Comentario comentario) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                comentario = em.find(Comentario.class, comentario.getCodigo());
                em.remove(comentario);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Comentario o</code> */
    public List<Comentario> getComentarioFindAll() {
        return getEntityManager().createNamedQuery("Comentario.findAll").getResultList();
    }

    public Corredor mergeCorredor(Corredor corredor) {
        return (Corredor)_mergeEntity(corredor);
    }

    public void removeCorredor(Corredor corredor) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                corredor = em.find(Corredor.class, corredor.getVendedorRut());
                em.remove(corredor);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Corredor o</code> */
    public List<Corredor> getCorredorFindAll() {
        return getEntityManager().createNamedQuery("Corredor.findAll").getResultList();
    }

    public Membresia mergeMembresia(Membresia membresia) {
        return (Membresia)_mergeEntity(membresia);
    }

    public void removeMembresia(Membresia membresia) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                membresia = em.find(Membresia.class, membresia.getCodigo());
                em.remove(membresia);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Membresia o</code> */
    public List<Membresia> getMembresiaFindAll() {
        return getEntityManager().createNamedQuery("Membresia.findAll").getResultList();
    }

    public Calificacion mergeCalificacion(Calificacion calificacion) {
        return (Calificacion)_mergeEntity(calificacion);
    }

    public void removeCalificacion(Calificacion calificacion) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                calificacion = em.find(Calificacion.class, new CalificacionPK(calificacion.getArticuloCodigo(), calificacion.getVendedorRut()));
                em.remove(calificacion);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Calificacion o</code> */
    public List<Calificacion> getCalificacionFindAll() {
        return getEntityManager().createNamedQuery("Calificacion.findAll").getResultList();
    }

    public Region mergeRegion(Region region) {
        return (Region)_mergeEntity(region);
    }

    public void removeRegion(Region region) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                region = em.find(Region.class, region.getCodigo());
                em.remove(region);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Region o</code> */
    public List<Region> getRegionFindAll() {
        return getEntityManager().createNamedQuery("Region.findAll").getResultList();
    }

    public Provincia mergeProvincia(Provincia provincia) {
        return (Provincia)_mergeEntity(provincia);
    }

    public void removeProvincia(Provincia provincia) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                provincia = em.find(Provincia.class, provincia.getCodigo());
                em.remove(provincia);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Provincia o</code> */
    public List<Provincia> getProvinciaFindAll() {
        return getEntityManager().createNamedQuery("Provincia.findAll").getResultList();
    }

    public Persona mergePersona(Persona persona) {
        return (Persona)_mergeEntity(persona);
    }

    public void removePersona(Persona persona) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                persona = em.find(Persona.class, persona.getVendedorRut());
                em.remove(persona);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Persona o</code> */
    public List<Persona> getPersonaFindAll() {
        return getEntityManager().createNamedQuery("Persona.findAll").getResultList();
    }

    public Empresacorredora mergeEmpresacorredora(Empresacorredora empresacorredora) {
        return (Empresacorredora)_mergeEntity(empresacorredora);
    }

    public void removeEmpresacorredora(Empresacorredora empresacorredora) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                empresacorredora = em.find(Empresacorredora.class, empresacorredora.getRut());
                em.remove(empresacorredora);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Empresacorredora o</code> */
    public List<Empresacorredora> getEmpresacorredoraFindAll() {
        return getEntityManager().createNamedQuery("Empresacorredora.findAll").getResultList();
    }

    public Inmobiliaria mergeInmobiliaria(Inmobiliaria inmobiliaria) {
        return (Inmobiliaria)_mergeEntity(inmobiliaria);
    }

    public void removeInmobiliaria(Inmobiliaria inmobiliaria) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                inmobiliaria = em.find(Inmobiliaria.class, inmobiliaria.getVendedorRut());
                em.remove(inmobiliaria);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Inmobiliaria o</code> */
    public List<Inmobiliaria> getInmobiliariaFindAll() {
        return getEntityManager().createNamedQuery("Inmobiliaria.findAll").getResultList();
    }

    public Calificacionv mergeCalificacionv(Calificacionv calificacionv) {
        return (Calificacionv)_mergeEntity(calificacionv);
    }

    public void removeCalificacionv(Calificacionv calificacionv) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                calificacionv = em.find(Calificacionv.class, new CalificacionvPK(calificacionv.getVendedorRut(), calificacionv.getVendedorRut1()));
                em.remove(calificacionv);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Calificacionv o</code> */
    public List<Calificacionv> getCalificacionvFindAll() {
        return getEntityManager().createNamedQuery("Calificacionv.findAll").getResultList();
    }

    public Consulta mergeConsulta(Consulta consulta) {
        return (Consulta)_mergeEntity(consulta);
    }

    public void removeConsulta(Consulta consulta) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                consulta = em.find(Consulta.class, consulta.getCodigo());
                em.remove(consulta);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Consulta o</code> */
    public List<Consulta> getConsultaFindAll() {
        return getEntityManager().createNamedQuery("Consulta.findAll").getResultList();
    }
}
