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
    String ciudad = null;
    String comuna = null;
    String provincia = null;
    String region = null; 
    String estructura=request.getParameter("estructura");
    String tipo=request.getParameter("tipo");      

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>MiInmueble</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <link type="text/css" rel="stylesheet" href="css/estilo_articulos.css"/>
    <script type="text/javascript" >
        function cambiaVentana(x){            
            alert(x);
            document.getElementById("myForm3").action = x;
            document.getElementById("myForm3").submit();
        }
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
        function cambio(){
            document.form_region.action = "";
            document.form_region.submit();
        }
            
    </script>
  </head>
  <body>            
  
    <div id="contenedor">
        <div class="principal">
    <%
                try{ 
                    String user = Funcion.userByIp((String)request.getRemoteAddr());
                    String str=request.getRequestURL()+"?";
                    Enumeration<String> paramNames = request.getParameterNames();
                    while (paramNames.hasMoreElements())
                    {
                        String paramName = paramNames.nextElement();
                        String[] paramValues = request.getParameterValues(paramName);
                        for (int i = 0; i < paramValues.length; i++) 
                        {
                            String paramValue = paramValues[i];
                            str=str + paramName + "=" + paramValue;
                        }
                        str=str+"&";
                    }
                    String pagina = str.substring(49,str.length()-1);
                    Funcion func = new Funcion();
                    
                    if (!func.getConexion().isClosed()){                                                        
          
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
                            
                            
                            out.print("<form name=\"form_logout\" action=\"logout.jsp\" method=\"POST\" class=\"logout\">");
                            out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                            out.print("<input id =\"cerrarsesion\" type=\"submit\" value=\"Cerrar Sesión\" />");
                            out.print("</form>");
                            //out.print("</span>");
                            //out.print("<span style=\"float:right;\">");                                                    
                            out.print("<form name=\"form_miperfil\" action=\"miperfil.jsp\" method=\"POST\" class=\"miperfil\">");
                            out.print("Bienvenido(a) "+func.findNombre(user)+" ");
                            out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                            out.print("<input id =\"miperfil\" type=\"submit\" value=\"Mi Perfil\" />");
                            out.print("</form>");
                            
                            out.print("<form id=\"myForm3\" name=\"myForm3\" action=\"\" method=\"POST\">");
                            out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                            out.print("</form>");
                        }
                        %>
            <form name="form_region" action="principal.jsp" id="form_region">
            
            <%
            region = "0";
            if(request.getParameter("region")!=null){
                region = request.getParameter("region");
            }
            provincia = "0";
            if(request.getParameter("provincia")!=null){
                provincia = request.getParameter("provincia");
            }  
            comuna = "0";
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
            <select id="estructura" name="estructura" size="1" onchange="javascript:cambio()" >
                <%
                    if(estructura==null){
                        estructura = "0";
                    }
                    out.print(Lugares.getEstructuras(estructura));
                %>
            </select>
            Tipo:
            <select id="tipo" name="tipo" size="1" onchange="javascript:cambio()">
                <%
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
            <%
            ciudad = request.getParameter("ciudad");
            if(ciudad==null ||ciudad.equalsIgnoreCase("")){
                out.print("<input type=\"text\" id=\"ciudad\" name=\"ciudad\" placeholder=\"Ingrese Ciudad\" onchange=\"javascript:cambio()\" />");
            }else {
                out.print("<input type=\"text\" id=\"ciudad\" name=\"ciudad\" value=\""+ciudad+"\" onchange=\"javascript:cambio()\" />");
            }%>            
            
        </form>
                        <%
                        out.print("</ div>");  
                        out.print("<div class=\"seccion1\">");
                        out.print("<h2 class=\"destacado\" >Articulos Destacado</h2>");
                        
                        out.print("<ul>"); 
                        System.out.println("Inicio Articulos Destacados");
                        for(List<String> salida :func.findArticulos("destacada","1")){
                            out.print("<li class =\"mejor\" type=\"disc\">");
                            out.print("<ul>");
                                out.print("<li type=\"disc\">");
                                out.print(salida.get(0));
                                out.print("</li>");
                                out.print("<li id=\"titulo\" type=\"disc\">");
                                out.print(salida.get(1));
                                out.print("</li>");
                                out.print("<li id=\"tipo\" type=\"disc\">");
                                out.print(salida.get(2));
                                out.print("</li>");
                                out.print("<li id=\"precio\" type=\"disc\">");
                                out.print(salida.get(3));
                                out.print("</li>");
                                out.print("<li id=\"calificacion\" type=\"disc\">");
                                out.print(Funcion.calificacionToString(Integer.parseInt(salida.get(4))));
                                out.print("</li>");
                                out.print("<li id=\"detalle\" type=\"disc\">");
                                out.print(salida.get(5));
                                out.print("</li>");
                                
                            
                            out.print("</ul>");
                            out.print("</li>");
                        }
                        System.out.println("Fin Articulos Destacados");
                        out.print("</ul>");
                        out.print("</ div>");
                        
                        

                        List<CampoValor> buscar = new ArrayList<CampoValor>();                        
                        if(comuna!=null && !comuna.equalsIgnoreCase("0")){
                            buscar.add(new CampoValor("comuna_codigo","=","'"+comuna+"'"));
                        }else if(provincia!=null && !provincia.equalsIgnoreCase("0")){
                            buscar.add(new CampoValor("comuna_codigo","Like","'"+provincia+"%'"));
                        }else if(region!=null && !region.equalsIgnoreCase("0")){
                            buscar.add(new CampoValor("comuna_codigo","Like","'"+region+"___'"));
                        }else{
                            buscar.add(new CampoValor("1","=","1"));
                        }
                        if(estructura!=null && !estructura.equalsIgnoreCase("0")){
                            buscar.add(new CampoValor("estructura","=","'"+estructura+"'"));
                        }
                        if(tipo!=null && !tipo.equalsIgnoreCase("0")){
                            buscar.add(new CampoValor("tipo","=","'"+tipo+"'"));
                        }
                        if(ciudad!=null && !ciudad.equalsIgnoreCase("")){
                            buscar.add(new CampoValor("ciudad","like","'"+ciudad+"%'"));
                        }
                        out.print("<h2 class=\"todos\">Todos los Articulos</h2>");
                        out.print("<div class=\"grupo\">");                        
                        out.print("<ul>"); 
                        System.out.println("Inicio Articulos Todos");
                        for(List<String> salida :func.findArticulos(buscar)){
                            out.print("<li type=\"disc\" class=\"articulos\">");
                            out.print("<ul>");
                            out.print("<li type=\"disc\">");
                                out.print(salida.get(0));
                                out.print("</li>");
                                out.print("<li id=\"titulo\" type=\"disc\">");
                                out.print(salida.get(1));
                                out.print("</li>");
                                out.print("<li id=\"tipo\" type=\"disc\">");
                                out.print(salida.get(2));
                                out.print("</li>");
                                out.print("<li id=\"precio\" type=\"disc\">");
                                out.print(salida.get(3));
                                out.print("</li>");
                                out.print("<li id=\"calificacion\" type=\"disc\">");
                                int cali = Integer.parseInt(salida.get(3));
                                out.print(Funcion.calificacionToString(cali));                                
                                out.print("</li>");
                                out.print("<li id=\"detalle\" type=\"disc\">");
                                out.print(salida.get(4));
                                out.print("</li>");
                            out.print("</ul>");
                            out.print("</li>");
                        }
                        System.out.println("Fin Articulos Todos");
                        out.print("</ul>");
                        out.print("</ div>");
                        
                        func.cerrarConexion();
                    } else{
                      out.println("fallo");
                    }
                }
                catch (Exception e){
                    out.println("Excepcion "+e);
                    e.printStackTrace();
                }
                
            %>                    
        
    </div>
    </div>
    </div>
    <div id="propaganda">        
            <div id="pro">
                <img style="width: 220px; height: 100px" src="http://image.portalinmobiliario.cl/Banners/1943_20141212154149.gif" alt="Banner" border="0" height="100" width="220">
            </div>        
        </div>
    
    
  </body>
</html>