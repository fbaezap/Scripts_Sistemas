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
    String Uname=request.getParameter("usuario");
    String Usecret=request.getParameter("contraseña");        
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reading Checkbox Data</title>
</head>
<body>
<%  
    String site;
    if(Uname==null || Usecret==null){
        System.out.println("No se pudo conectar");
        site = new String("login.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site); 
    }else if(Funcion.Chequea(Uname, Usecret)){
        System.out.println("Validados");
        System.out.println("usuario: "+Uname);
        session.setAttribute("user", Uname);    
        System.out.println("usuario2: "+session.getAttribute("user"));
        site = new String((String)request.getParameter("lastPagina"));
        String alert = "imposible conectar";
        try{
            alert = Funcion.conectaUsuario(Uname,request.getRemoteAddr());
        
        }catch(Exception e){
            out.print("alert('Imposible Conectar')");            
        }
        out.print("alert('"+alert+"')");
        
        out.print("<form id=\"myForm\" name=\"myForm\" action=\""+site+"\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+site+"\"/>");
        out.print("</form>");
        %>
        <script type="text/javascript" >
            document.getElementById("myForm").submit();
        </script>
        <%
        
    }else{
        out.print("alert('Contraseña Incorrecta')");
        site = new String((String)request.getParameter("lastPagina"));
        
        out.print("<form id=\"myForm2\" name=\"myForm2\" action=\"\" method=\"POST\">");
        out.print("<input type=\"hidden\" name=\"lastPagina\" value=\""+site+"\"/>");
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