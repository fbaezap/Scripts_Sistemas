<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="javax.sql.*"%>
<%@ page language="java" import="javax.ejb.*"%>
<%@ page language="java" import="javax.naming.*"%>
<%@ page language="java" import="oracle.*"%>
<%@ page language="java" import="oracle.functions.*"%>
<%@ page language="java" import="oracle.entities.*"%>
<%@ page language="java" import="javax.xml.bind.DatatypeConverter"%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" pageEncoding="ISO-8859-1"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>MiInmueble</title>
        <link rel="stylesheet" type="text/css" href="css/estiloindex.css" />
        <meta http-equiv="content-type" content="text/html; charset=windows-1252">
    </head>
    <body>
        <div id="buscador">
         <section>

        <%
            try{                                        
                
                    String user = Funcion.userByIp((String)request.getRemoteAddr());
                
                    Funcion func = new Funcion();
                    if (!func.getConexion().isClosed()){
                        out.print("<div>");      
                        System.out.println("usuario en principal"+user);                        
                        if(user==null){
                            out.print("<form name=\"form_login\" action=\"login.jsp\" method=\"POST\" class=\"login\">");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\"index.jsp\"/>");
                            out.print("<input id =\"acceder\" type=\"submit\" value=\"Acceder\" />");
                            out.print("</form>");
                        }else{
                            out.print("<span style=\"float:right;\">");
                            out.print("<form name=\"form_logout\" action=\"logout.jsp\" method=\"POST\" class=\"logout\">");
                            out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\"index.jsp\"/>");
                            out.print("<input id =\"cerrarsesion\" type=\"submit\" value=\"Cerrar Sesión\" />");
                            out.print("</form>");
                            out.print("</span>");
                            out.print("<span style=\"float:right;\">");                                                    
                            out.print("<form name=\"form_miperfil\" action=\"miperfil.jsp\" method=\"POST\" class=\"miperfil\">");
                            out.print("Bienvenido(a) "+func.findNombre(user)+" ");
                            out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\"index.jsp\"/>");
                            out.print("<input id =\"miperfil\" type=\"submit\" value=\"Mi Perfil\" />");
                            out.print("</form>");
                            out.print("</span>");
                        }
                        func.cerrarConexion();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
        %>
        <script type="text/javascript">
            function cambioR(){
                document.form_region.action = "";
                document.form_region.ultimocambiado.value = "R";
                document.form_region.submit();
            }
            function cambioP(){
                document.form_region.action = "";
                document.form_region.ultimocambiado.value = "P";
                document.form_region.submit();
            }
            function cambioC(){
                document.form_region.action = "";
                document.form_region.ultimocambiado.value = "C";
                document.form_region.submit();
            }
        </script>
        
        <form name="form_region" action="principal.jsp" id="form_region">
            
            <%
            String region = "0";
            if(request.getParameter("region")!=null){
                region = request.getParameter("region");
            }
            String provincia = "0";
            if(request.getParameter("provincia")!=null){
                provincia = request.getParameter("provincia");
            }  
            String comuna = "0";
            if(request.getParameter("comuna")!=null){
                comuna = request.getParameter("comuna");
            }  
            String ultimocambiado = request.getParameter("ultimocambiado");
            if(ultimocambiado==null){
                ultimocambiado = "R";
            }
            
            String[] objetos = Lugares.getObjetos(comuna,provincia,region,ultimocambiado);
            %>
            <input type="hidden" name="ultimocambiado"/>
            Estructura:
            <select id="estructura" name="estructura" size="1" >
                <%
                    String estructura = request.getParameter("estructura");
                    if(estructura==null){
                        estructura = "0";
                    }
                    out.print(Lugares.getEstructuras(estructura));
                %>
            </select>
            Tipo:
            <select id="tipo" name="tipo" size="1" >
                <%
                    String tipo = request.getParameter("tipo");
                    if(tipo==null){
                        tipo = "0";
                    }
                    out.print(Lugares.getTipos(tipo));
                %>            
            </select>
            Region:
            <select id="region" name="region" size="1" onchange="javascript:cambioR()">
                <%
                    out.print(objetos[0]);
                %>
            </select>
            Provincia:
            <select id="provincia" name="provincia" size="1" onchange="javascript:cambioP()">
                <%
                    out.print(objetos[1]);
                %>
            </select>
            Comuna:
            <select id="comuna" name="comuna" size="1" onchange="javascript:cambioC()">
                <%
                    out.print(objetos[2]);
                %>
            </select>
            <%String ciudad = request.getParameter("ciudad");
            if(ciudad==null){
                out.print("<input type=\"text\" id=\"ciudad\" name=\"ciudad\" placeholder=\"Ingrese Ciudad\" />");
            }else {
                out.print("<input type=\"text\" id=\"ciudad\" name=\"ciudad\" value=\""+ciudad+"\" />");
            }%>            
            
            <input type="submit" value="Buscar" id="buscar"/>
        </form>
        </section>
        </div>
    </body>
</html>