<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="javax.sql.*"%>
<%@ page language="java" import="javax.ejb.*"%>
<%@ page language="java" import="javax.naming.*"%>
<%@ page language="java" import="oracle.*"%>
<%@ page language="java" import="oracle.entities.*"%>

<%
            Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        final Context context = new InitialContext( env );
        SessionEJB sessionEJB = (SessionEJB)context.lookup("MiInmueble-ModeloVistas-SessionEJB#oracle.SessionEJB");
%>

<HTML>

<BODY>

<%

//System.out.println( "Calculating esops pay out for employee" );

Articulo a = sessionEJB.getArticuloFindAll().get(0);
Articulo a2 = sessionEJB.getArticuloFindAll().get(1);

%>

Articulo 1: <%= a.getTitulo() %>
Articulo 2: <%= a2.getTitulo() %>

</BODY>

</HTML>
