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
    String user=request.getParameter("user");
    String pagina=request.getParameter("lastPagina");        
    String artiId=request.getParameter("artiId");  
    String mensaje=request.getParameter("mensaje");  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reading Checkbox Data</title>
</head>
<body>
<%  
    if(user==null || pagina==null || artiId==null || mensaje==null){
        pagina = new String("index.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", pagina);
        
    }else{      
        out.print("<form id=\"myForm2\" name=\"myForm2\" action=\""+pagina+"\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
        out.print("</form>");
        try{
            oracle.functions.Comentario.addComentario(artiId, user, mensaje);
        }catch(Exception e){
            e.printStackTrace();
        }
        %>
        <script type="text/javascript" >
            document.getElementById("myForm2").submit();
        </script>
        <% 
    }
    %>
</body>
</html>