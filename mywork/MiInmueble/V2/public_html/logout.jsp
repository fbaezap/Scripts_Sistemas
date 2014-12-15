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
    String Uname=request.getParameter("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reading Checkbox Data</title>
</head>
<body>
<%  
    String user = (String) request.getParameter("user");
    String pagina = (String)request.getParameter("lastPagina");
    if(user==null){
        out.print("<form id=\"myForm1\" name=\"myForm1\" action=\"login.jsp\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
        out.print("</form>");
        %>
        <script type="text/javascript" >
            document.getElementById("myForm1").submit();
        </script>
        <%
    }else{
        Funcion.desconectaUsuario(user);
        out.print("<form id=\"myForm2\" name=\"myForm2\" action=\""+pagina+"\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+pagina+"\"/>");
        out.print("</form>");
        %>
        <script type="text/javascript" >
            document.getElementById("myForm2").submit();
        </script>
        <%
    }
    %>
</body>
</html>