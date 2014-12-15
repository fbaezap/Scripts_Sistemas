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


<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%    
    String artiId = request.getParameter("artId")  ;
    out.println(artiId);
    out.print( request.getRemoteAddr() );    
    out.print( request.getRemoteHost() );
%>

<html >
	<head>
            <title>MiInmueble</title>            
            <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
            
            <script type="text/javascript">
                function cambiaGrande(x){
                    document.getElementById('imagengrande').src = x;
                }
                function submitGuarda(){
                    document.getElementById("form_lista").action = "guarda.jsp";
                    document.getElementById("form_lista").submit();
                
                }
                function submitCancela(){
                    document.getElementById("form_lista").action = "";
                    document.getElementById("form_lista").submit();
                
                }
            </script>
        </head>
        <body>
        <%
                try{                                        
                
                    String user = Funcion.userByIp((String)request.getRemoteAddr());
                
                    Funcion func = new Funcion();
                    if (!func.getConexion().isClosed()){
                        out.print("<div>");  
                        String pagina =  "detalle.jsp?artId="+artiId;        
                        System.out.println("usuario en principal"+user);                        
                        if(user==null){
                            out.print("<form name=\"form_login\" action=\"login.jsp\" method=\"POST\" class=\"login\">");
                            out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
                            out.print("<input id =\"acceder\" type=\"submit\" value=\"Acceder\" />");
                            out.print("</form>");
                        }else{
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
                        }
                        out.print("</ div>");  
                        //if(!FuncArticulos.esMiArticulo(user, artiId)){
                            List<CampoValor> listacampo= new ArrayList<CampoValor>();
                            CampoValor c = new CampoValor("codigo",artiId);
                            listacampo.add(c);
                            String[] cosas = new String[16];
                            cosas[0]="titulo";
                            cosas[1]="tipo";
                            cosas[2]="monto";
                            cosas[3]="direccion";
                            cosas[4]="descripcion";
                            cosas[5]="SUPERFICIE";
                            cosas[6]="ESTRUCTURA";
                            cosas[7]="DESTACADA";
                            cosas[8]="SUPERFICIECONSTRUIDA";
                            cosas[9]="PIEZAS";
                            cosas[10]="BAÑOS";
                            cosas[11]="PISOS";
                            cosas[12]="CIUDAD";
                            cosas[13]="CALIFICACION";
                            cosas[14]="COMUNA_CODIGO";
                            cosas[15]="CODI_INTERNO";
                            List<String> muestra = new ArrayList<String>();
                            for(int i=0;i<15;i++){
                                muestra.add(cosas[i]);
                            }
                            boolean mi_articulo  = FuncArticulos.esMiArticulo(user, artiId);
                            if(mi_articulo){                                
                                muestra.add(cosas[15]);
                            }
                            for(Funcion.ArticuloDetalle salida :func.findArticulosDetalle(listacampo,muestra,true)){
                                List<String> textos = salida.getTextos();
                                List<String> imagenes = salida.getImagenes();
                                List<List<String>> comentarios = salida.getComentarios();
                                out.print("<h2>"+ textos.get(0)+"</h2>");
                                out.print("<div>");                        
                                if(mi_articulo){
                                    out.print("<form id=\"form_lista\" name=\"form_lista\" action=\"\" method=\"POST\" class=\"lista\">");                                
                                    out.print("<input type=\"hidden\" name=\"codigo\" value=\""+artiId+"\"/>");
                                    out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\"/>");
                                }
                                out.print("<ul>");                             
                                if(mi_articulo){
                                    out.print("<li type=\"disc\">");
                                    out.print("Titulo");
                                    out.print("<input type=\"text\" name=\""+cosas[0]+"\" value=\""+textos.get(0)+"\" />");
                                    out.print("</li>");
                                }
                                for(int i=1; i<textos.size();i++){
                                    if(!mi_articulo){
                                        if(cosas[i].equalsIgnoreCase("destacada")){
                                            out.print("<li type=\"disc\">");
                                            if(textos.get(i).equalsIgnoreCase("0")){
                                                out.print("No Destacada");
                                            }else{
                                                out.print("Destacada");
                                            }
                                            out.print("</li>");    
                                        }else if(cosas[i].equalsIgnoreCase("comuna_codigo")){
                                            out.print("<li type=\"disc\">");
                                            out.print("Comuna : ");
                                            out.print(Lugares.getNombre("comuna", textos.get(i)));
                                            out.print(", ");
                                            out.print(Lugares.getNombre("Provincia", textos.get(i).substring(0, 3)));
                                            out.print(", ");
                                            out.print(Lugares.getNombre("Region", textos.get(i).substring(0, 2)));
                                            out.print("</li>");
                                        }else if(cosas[i].equalsIgnoreCase("superficieconstruida")){
                                            out.print("<li type=\"disc\">");
                                            out.print("Superficie Construida: ");
                                            out.print(textos.get(i));
                                            out.print("</li>");
                                        }else{
                                            out.print("<li type=\"disc\">");
                                            out.print(cosas[i].substring(0,1).toUpperCase() + cosas[i].substring(1).toLowerCase()+": ");
                                            out.print(textos.get(i));
                                            out.print("</li>");
                                        }
                                    }else{
                                        if(cosas[i].equalsIgnoreCase("destacada")){
                                            out.print("<li type=\"disc\">");
                                            if(textos.get(i).equalsIgnoreCase("0")){
                                                out.print("No Destacada");
                                            }else{
                                                out.print("Destacada");
                                            }
                                            out.print("</li>");    
                                        }else if(cosas[i].equalsIgnoreCase("comuna_codigo")){
                                            out.print("<li type=\"disc\">");
                                            out.print("Comuna : ");
                                            out.print(Lugares.getNombre("comuna", textos.get(i)));
                                            out.print(", ");
                                            out.print(Lugares.getNombre("Provincia", textos.get(i).substring(0, 3)));
                                            out.print(", ");
                                            out.print(Lugares.getNombre("Region", textos.get(i).substring(0, 2)));
                                            out.print("</li>");
                                        }else if(cosas[i].equalsIgnoreCase("superficieconstruida")){
                                            out.print("<li type=\"disc\">");
                                            out.print("Superficie Construida: ");
                                            out.print("<input type=\"text\" name=\""+cosas[i]+"\" value=\""+textos.get(i)+"\" />");
                                            out.print("</li>");
                                        }else if(cosas[i].equalsIgnoreCase("calificacion")){
                                            out.print("<li type=\"disc\">");
                                            out.print("Calificacion: ");
                                            out.print("<input type=\"text\" name=\""+cosas[i]+"\" value=\""+textos.get(i)+"\" readonly/>");
                                            out.print("</li>");
                                        }else if(cosas[i].equalsIgnoreCase("codi_interno")) {
                                            out.print("<li type=\"disc\">");
                                            String nom = "Codigo Interno";
                                            out.print(nom);
                                            if(textos.get(i)!=null){
                                                out.print("<input type=\"text\" name=\""+cosas[i]+"\" value=\""+textos.get(i)+"\" />");
                                            }else{
                                                out.print("<input type=\"text\" name=\""+cosas[i]+"\" value=\""+""+"\" />");
                                            }
                                            out.print("</li>");
                                        }else{
                                            out.print("<li type=\"disc\">");
                                            String nom = cosas[i].substring(0,1).toUpperCase() + cosas[i].substring(1).toLowerCase()+": ";
                                            out.print(nom);
                                            out.print("<input type=\"text\" name=\""+cosas[i]+"\" value=\""+textos.get(i)+"\" />");
                                            out.print("</li>");
                                        }
                                    }
                                }                                
                                out.print("</ul>");
                                if(mi_articulo){
                                    out.print("</ form>");
                                    out.print("<input id=\"guardar\" name=\"guardar\" type=\"button\" value=\"Guardar\" onclick=\"javascript:submitGuarda()\" />");
                                    out.print("<input id=\"cancelar\" name=\"cancelar\" type=\"button\" value=\"Cancelar\" onclick=\"javascript:submitCancela()\"/>");
                                }
                                out.print("</ div>");
                                
                                out.print("<h2>"+"Imagenes"+"</h2>");
                                out.print("<div>");                  
                                out.print("<img src=\"mt05.jpg\" id=\"imagengrande\" alt=\"Foto Pesada\" />");
                                out.print("<ul>");                             
                                for(String img : imagenes){
                                    out.print("<li type=\"disc\">");
                                    out.print(img);
                                    out.print("</li>");
                                }                                
                                out.print("</ul>");
                                out.print("</ div>");
                               
                                out.print("<h2>"+"Comentarios"+"</h2>");
                                out.print("<div>");                  
                                out.print("<ul>");                             
                                for(List<String> comentario : comentarios){
                                    out.print("<li type=\"disc\">");
                                    out.print("<ul>");              
                                    for(String lin: comentario){               
                                        out.print("<li type=\"disc\">");
                                        out.print(lin);
                                        out.print("</li>");
                                    }
                                    out.print("</ul>");
                                    out.print("</li>");
                                }                                
                                out.print("</ul>");
                                out.print("</ div>");
                                
                                
                                if(user!=null){
                                    out.print("<h2>"+"Deja tu Comentario"+"</h2>");
                                    out.print("<form action=\"comentar.jsp\" method=\"POST\">");
                                    out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                                    out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");                                
                                    out.print("<input type=\"hidden\" name=\"artiId\" value=\""+artiId+"\"/>");
                                    out.print("<textarea name=\"mensaje\" cols=\"40\" rows=\"5\"></textarea>");                                
                                    out.print("<br>");
                                    out.print("<input type=\"submit\" value=\"Comentar\" />");
                                    out.print("</form>");
                                    
                                }else{
                                    out.print("<h2>"+"Accede para dejar un comentario"+"</h2>");
                                }
                            
                        }
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
        </body>
</html>