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
    List<String> campo = new ArrayList<String>();
    List<String> valor = new ArrayList<String>();
    String[] resultados = new String[16];
    for(int i=0;i<resultados.length;i++){
        resultados[i] = request.getParameter(cosas[i]);
        if(resultados[i]!=null){
            System.out.println(resultados[i]);
            campo.add(cosas[i]);
            valor.add(resultados[i]);
        }
    }
    String artiId = request.getParameter("codigo");
    String user=request.getParameter("user");
    String pagina=request.getParameter("lastPagina"); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reading Checkbox Data</title>
</head>
<body>
<%
    if(user==null){
        out.print("<form id=\"myForm\" name=\"myForm\" action=\""+"index.jsp"+"\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+"index.jsp"+"\"/>");
        out.print("</form>");
        %>
        <script type="text/javascript" >
            document.getElementById("myForm").submit();
        </script>
        <%
    }else{
        try{
            FuncArticulos.editaArticulo(campo,valor,artiId);
        }catch(Exception e){
            e.printStackTrace();
        }
        out.print("<form id=\"myForm\" name=\"myForm\" action=\""+pagina+"\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
        out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\"/>");
        out.print("</form>");
        %>
        <script type="text/javascript" >
            document.getElementById("myForm").submit();
        </script>
        <%    
    }
%>                            
</body>
</html>
