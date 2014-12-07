package oracle;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HRFacadeClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            HRFacade hRFacade = (HRFacade)context.lookup("MiInmuebleV1App-Model-HRFacade#oracle.HRFacade");
            /*for (Conectado conectado : (List<Conectado>)hRFacade.getConectadoFindAll()) {
                printConectado(conectado);
            }
            for (Vendedor vendedor : (List<Vendedor>)hRFacade.getVendedorFindAll()) {
                printVendedor(vendedor);
            }
            for (Comuna comuna : (List<Comuna>)hRFacade.getComunaFindAll()) {
                printComuna(comuna);
            }
            for (Articulo articulo : (List<Articulo>)hRFacade.getArticuloFindAll()) {
                printArticulo(articulo);
            }*/
            for (Articulo articulo : (List<Articulo>)hRFacade.getArticuloFindByDestacada("1")) {
                printArticulo(articulo);
            }
            /*for (Fotos fotos : (List<Fotos>)hRFacade.getFotosFindAll()) {
                printFotos(fotos);
            }
            for (Comentario comentario : (List<Comentario>)hRFacade.getComentarioFindAll()) {
                printComentario(comentario);
            }
            for (Corredor corredor : (List<Corredor>)hRFacade.getCorredorFindAll()) {
                printCorredor(corredor);
            }
            for (Membresia membresia : (List<Membresia>)hRFacade.getMembresiaFindAll()) {
                printMembresia(membresia);
            }
            for (Calificacion calificacion : (List<Calificacion>)hRFacade.getCalificacionFindAll()) {
                printCalificacion(calificacion);
            }
            for (Region region : (List<Region>)hRFacade.getRegionFindAll()) {
                printRegion(region);
            }
            for (Provincia provincia : (List<Provincia>)hRFacade.getProvinciaFindAll()) {
                printProvincia(provincia);
            }
            for (Persona persona : (List<Persona>)hRFacade.getPersonaFindAll()) {
                printPersona(persona);
            }
            for (Empresacorredora empresacorredora : (List<Empresacorredora>)hRFacade.getEmpresacorredoraFindAll()) {
                printEmpresacorredora(empresacorredora);
            }
            for (Inmobiliaria inmobiliaria : (List<Inmobiliaria>)hRFacade.getInmobiliariaFindAll()) {
                printInmobiliaria(inmobiliaria);
            }
            for (Calificacionv calificacionv : (List<Calificacionv>)hRFacade.getCalificacionvFindAll()) {
                printCalificacionv(calificacionv);
            }
            for (Consulta consulta : (List<Consulta>)hRFacade.getConsultaFindAll()) {
                printConsulta(consulta);
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printConectado(Conectado conectado) {
        System.out.println( "horainicio = " + conectado.getHorainicio() );
        System.out.println( "ip = " + conectado.getIp() );
        System.out.println( "vendedorRut = " + conectado.getVendedorRut() );
        System.out.println( "vendedor = " + conectado.getVendedor() );
    }

    private static void printVendedor(Vendedor vendedor) {
        System.out.println( "calificacion = " + vendedor.getCalificacion() );
        System.out.println( "contrase�a = " + vendedor.getContrase�a() );
        System.out.println( "correo = " + vendedor.getCorreo() );
        System.out.println( "rut = " + vendedor.getRut() );
        System.out.println( "telefono = " + vendedor.getTelefono() );
        System.out.println( "tipo = " + vendedor.getTipo() );
        System.out.println( "usuario = " + vendedor.getUsuario() );
        System.out.println( "corredorList = " + vendedor.getCorredorList() );
        System.out.println( "membresia = " + vendedor.getMembresia() );
        System.out.println( "comentarioList = " + vendedor.getComentarioList() );
        System.out.println( "personaList = " + vendedor.getPersonaList() );
        System.out.println( "inmobiliariaList = " + vendedor.getInmobiliariaList() );
        System.out.println( "articuloList = " + vendedor.getArticuloList() );
        System.out.println( "calificacionvList = " + vendedor.getCalificacionvList() );
        System.out.println( "calificacionvList1 = " + vendedor.getCalificacionvList1() );
        System.out.println( "calificacionList = " + vendedor.getCalificacionList() );
        System.out.println( "conectadoList = " + vendedor.getConectadoList() );
    }

    private static void printComuna(Comuna comuna) {
        System.out.println( "codigo = " + comuna.getCodigo() );
        System.out.println( "nombre = " + comuna.getNombre() );
        System.out.println( "provincia = " + comuna.getProvincia() );
        System.out.println( "articuloList = " + comuna.getArticuloList() );
    }

    private static void printArticulo(Articulo articulo) {
        System.out.println( "ba�os = " + articulo.getBa�os() );
        System.out.println( "ciudad = " + articulo.getCiudad() );
        System.out.println( "codigo = " + articulo.getCodigo() );
        System.out.println( "codiInterno = " + articulo.getCodiInterno() );
        System.out.println( "descripcion = " + articulo.getDescripcion() );
        System.out.println( "destacada = " + articulo.getDestacada() );
        System.out.println( "direccion = " + articulo.getDireccion() );
        System.out.println( "estructura = " + articulo.getEstructura() );
        System.out.println( "fecha = " + articulo.getFecha() );
        System.out.println( "monto = " + articulo.getMonto() );
        System.out.println( "piezas = " + articulo.getPiezas() );
        System.out.println( "pisos = " + articulo.getPisos() );
        System.out.println( "superficie = " + articulo.getSuperficie() );
        System.out.println( "superficieconstruida = " + articulo.getSuperficieconstruida() );
        System.out.println( "tipo = " + articulo.getTipo() );
        System.out.println( "titulo = " + articulo.getTitulo() );
        System.out.println( "fotosList = " + articulo.getFotosList() );
        System.out.println( "consultaList = " + articulo.getConsultaList() );
        System.out.println( "comuna = " + articulo.getComuna() );
        System.out.println( "calificacionList = " + articulo.getCalificacionList() );
        System.out.println( "vendedor = " + articulo.getVendedor() );
        System.out.println( "comentarioList = " + articulo.getComentarioList() );
    }

    private static void printFotos(Fotos fotos) {
        System.out.println( "id = " + fotos.getId() );
        System.out.println( "imagen = " + fotos.getImagen() );
        System.out.println( "articulo = " + fotos.getArticulo() );
    }

    private static void printComentario(Comentario comentario) {
        System.out.println( "codigo = " + comentario.getCodigo() );
        System.out.println( "fecha = " + comentario.getFecha() );
        System.out.println( "mensaje = " + comentario.getMensaje() );
        System.out.println( "vendedor = " + comentario.getVendedor() );
        System.out.println( "articulo = " + comentario.getArticulo() );
    }

    private static void printCorredor(Corredor corredor) {
        System.out.println( "apellidos = " + corredor.getApellidos() );
        System.out.println( "genero = " + corredor.getGenero() );
        System.out.println( "nombre = " + corredor.getNombre() );
        System.out.println( "vendedorRut = " + corredor.getVendedorRut() );
        System.out.println( "vendedor = " + corredor.getVendedor() );
        System.out.println( "empresacorredora = " + corredor.getEmpresacorredora() );
    }

    private static void printMembresia(Membresia membresia) {
        System.out.println( "codigo = " + membresia.getCodigo() );
        System.out.println( "ndestacados = " + membresia.getNdestacados() );
        System.out.println( "nfotos = " + membresia.getNfotos() );
        System.out.println( "nombre = " + membresia.getNombre() );
        System.out.println( "npublicaciones = " + membresia.getNpublicaciones() );
        System.out.println( "precio = " + membresia.getPrecio() );
        System.out.println( "tipo = " + membresia.getTipo() );
        System.out.println( "vendedorList = " + membresia.getVendedorList() );
    }

    private static void printCalificacion(Calificacion calificacion) {
        System.out.println( "articuloCodigo = " + calificacion.getArticuloCodigo() );
        System.out.println( "calificacion = " + calificacion.getCalificacion() );
        System.out.println( "vendedorRut = " + calificacion.getVendedorRut() );
        System.out.println( "articulo = " + calificacion.getArticulo() );
        System.out.println( "vendedor = " + calificacion.getVendedor() );
    }

    private static void printRegion(Region region) {
        System.out.println( "codigo = " + region.getCodigo() );
        System.out.println( "nombre = " + region.getNombre() );
        System.out.println( "posicion = " + region.getPosicion() );
        System.out.println( "provinciaList = " + region.getProvinciaList() );
    }

    private static void printProvincia(Provincia provincia) {
        System.out.println( "codigo = " + provincia.getCodigo() );
        System.out.println( "nombre = " + provincia.getNombre() );
        System.out.println( "comunaList = " + provincia.getComunaList() );
        System.out.println( "region = " + provincia.getRegion() );
    }

    private static void printPersona(Persona persona) {
        System.out.println( "apellidos = " + persona.getApellidos() );
        System.out.println( "genero = " + persona.getGenero() );
        System.out.println( "nombre = " + persona.getNombre() );
        System.out.println( "vendedorRut = " + persona.getVendedorRut() );
        System.out.println( "vendedor = " + persona.getVendedor() );
    }

    private static void printEmpresacorredora(Empresacorredora empresacorredora) {
        System.out.println( "correo = " + empresacorredora.getCorreo() );
        System.out.println( "logo = " + empresacorredora.getLogo() );
        System.out.println( "nombre = " + empresacorredora.getNombre() );
        System.out.println( "paginaweb = " + empresacorredora.getPaginaweb() );
        System.out.println( "rut = " + empresacorredora.getRut() );
        System.out.println( "corredorList = " + empresacorredora.getCorredorList() );
    }

    private static void printInmobiliaria(Inmobiliaria inmobiliaria) {
        System.out.println( "domicilioComercial = " + inmobiliaria.getDomicilioComercial() );
        System.out.println( "domicilioLegal = " + inmobiliaria.getDomicilioLegal() );
        System.out.println( "logo = " + inmobiliaria.getLogo() );
        System.out.println( "nombre = " + inmobiliaria.getNombre() );
        System.out.println( "paginaweb = " + inmobiliaria.getPaginaweb() );
        System.out.println( "tipoSociedad = " + inmobiliaria.getTipoSociedad() );
        System.out.println( "vendedorRut = " + inmobiliaria.getVendedorRut() );
        System.out.println( "vendedor = " + inmobiliaria.getVendedor() );
    }

    private static void printCalificacionv(Calificacionv calificacionv) {
        System.out.println( "calificacion = " + calificacionv.getCalificacion() );
        System.out.println( "vendedorRut = " + calificacionv.getVendedorRut() );
        System.out.println( "vendedorRut1 = " + calificacionv.getVendedorRut1() );
        System.out.println( "vendedor = " + calificacionv.getVendedor() );
        System.out.println( "vendedor1 = " + calificacionv.getVendedor1() );
    }

    private static void printConsulta(Consulta consulta) {
        System.out.println( "codigo = " + consulta.getCodigo() );
        System.out.println( "correoRespuesta = " + consulta.getCorreoRespuesta() );
        System.out.println( "hora = " + consulta.getHora() );
        System.out.println( "mensaje = " + consulta.getMensaje() );
        System.out.println( "nombre = " + consulta.getNombre() );
        System.out.println( "articulo = " + consulta.getArticulo() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
