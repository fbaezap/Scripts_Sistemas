<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="javax.sql.*"%>
<%@ page language="java" import="javax.ejb.*"%>
<%@ page language="java" import="javax.naming.*"%>
<%@ page language="java" import="oracle.*"%>
<%@ page language="java" import="oracle.functions.*"%>
<%@ page language="java" import="oracle.entities.*"%>
<%@ page language="java" import="javax.xml.bind.DatatypeConverter"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
    //HttpSession actual = request.getSession(true);
    //String id = actual.getId();
    //String nombre = (String)actual.getAttribute("id2");
    String ciudad=request.getParameter("ciudad");
    String comuna=request.getParameter("comuna");
    String provincia=request.getParameter("provincia");
    String region=request.getParameter("region"); 
    String estructura=request.getParameter("estructura");
    String tipo=request.getParameter("tipo");      

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>MiInmueble</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <link type="text/css" rel="stylesheet" href="css/estiloindex.css"/>
    <script type="text/javascript" >
        function cambiaVentana(x){            
            alert(x);
            document.getElementById("myForm3").action = x;
            document.getElementById("myForm3").submit();
        }
            
    </script>
  </head>
  <body>
    
     
    <%
                try{ 
                    String user = Funcion.userByIp((String)request.getRemoteAddr());
                    String pagina = "miperfil.jsp";
                    Funcion func = new Funcion();
                    if(user==null){
                        System.out.println("usuario en principal"+"null");
                        out.print("<form name=\"form_login\" action=\"login.jsp\" method=\"POST\" class=\"login\">");
                        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                        out.print("<input id =\"acceder\" type=\"submit\" value=\"Acceder\" />");
                        out.print("</form>");
                        
                        out.print("<form id=\"myForm3\" name=\"myForm3\" action=\"\" method=\"POST\">");
                        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                        out.print("</form>");
                    }else{
                        System.out.println("usuario en principal"+user);
                        
                        out.print("<span style=\"float:right;\">");
                        out.print("<form name=\"form_logout\" action=\"logout.jsp\" method=\"POST\" class=\"logout\">");
                        out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                        out.print("<input id =\"cerrarsesion\" type=\"submit\" value=\"Cerrar Sesión\" />");
                        out.print("</form>");
                        out.print("</span>");
                        out.print("<span style=\"float:right;\">");                                                    
                        out.print("<form name=\"form_miperfil\" action=\"miperfil.jsp\" method=\"POST\" class=\"miperfil\">");
                        out.print("Bienvenido(a) "+func.findNombre(user)+" ");
                        out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                        out.print("<input id =\"miperfil\" type=\"submit\" value=\"Mi Perfil\" />");
                        out.print("</form>");
                        out.print("</span>");
                        
                        out.print("<form id=\"myForm3\" name=\"myForm3\" action=\"\" method=\"POST\">");
                        out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                        out.print("</form>");
                    }
                    out.print("<h2>Mis Datos</h2>");
                    out.print("<ul>");
                    for(List<String> salidas: FuncVendedor.getDatosByRut(user)){
                        out.print("<li type=\"disc\">");                                                                        
                        for(String salida: salidas){
                            out.print(salida);
                        }                            
                        out.print("</li>");  
                    }
                    out.print("</ul>");
                    
                    List<CampoValor> buscar = new ArrayList<CampoValor>();
                    buscar.add(new CampoValor("vendedor_rut","'"+user+"'"));
                    
                    List<String> mostrar = new ArrayList<String>();
                    mostrar.add("titulo");
                    
                    out.print("<h2>Mis Articulos</h2>");
                    out.print("<ul>"); 
                    for(List<String> articulo: FuncArticulos.findArticulos(buscar, mostrar, false)){
                        out.print("<li type=\"disc\">");                                                                        
                        for(String dato: articulo){
                            out.print(dato+" ");
                        }                            
                        out.print("</li>"); 
                    }
                    out.print("</ul>");
                        
                }catch(Exception e){
                    e.printStackTrace();
                }
                    %>
    </body> 
</html>
                    