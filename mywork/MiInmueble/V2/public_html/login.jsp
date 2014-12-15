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

<%
session.setAttribute("lastPagina", (String) request.getParameter("lastPagina"));
System.out.println("usuario de principal: "+(String) request.getParameter("user"));
System.out.println("pagina de principal: "+(String) request.getParameter("lastPagina"));
System.out.println("pagina de login: "+session.getAttribute("lastPagina"));
%>
<html>
    <body>
        <form action="valida.jsp" method="POST">
            Rut: <input type="text" name="usuario" />
            <br>
            Contraseña: <input type="password" name="contraseña"  /> 
            <br>
            <input type="hidden" name="lastPagina" value="<%=session.getAttribute("lastPagina")%>" /> 
            <input type="submit" value="Ingresar" />
        </form>
    </body>
</html>