<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="javax.sql.*"%>
<%@ page language="java" import="javax.ejb.*"%>
<%@ page language="java" import="javax.naming.*"%>
<%@ page language="java" import="oracle.*"%>
<%@ page language="java" import="oracle.entities.*"%>

<%
    /*HttpSession actual = request.getSession(true);
    String id = actual.getId();
    String nombre = (String)actual.getAttribute("id2");
    String ciudad=request.getParameter("ciudad");
    String region=request.getParameter("region"); 
    String pais=request.getParameter("pais");
    String lat=request.getParameter("lat");
    String lon=request.getParameter("lon");
    String busqueda=request.getParameter("busq");
    String busqueda1=request.getParameter("buscar");*/
    
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        final Context context =  new InitialContext( env );
        SessionEJB sessionEJB = (SessionEJB)context.lookup("MiInmueble-Vistas-SessionEJB#oracle.SessionEJB");
 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
            <title>MiInmueble</title>
        </head>
        <body>
            <%
                Inmobiliaria i = sessionEJB.getInmobiliariaFindRut( "90000000-2" ).get(0);
                out.print("<h2>"+i.getNombre()+"</h2>");
                out.print("<h3>"+i.getNombre()+"</h3>");                    
                
            %>
        </body>