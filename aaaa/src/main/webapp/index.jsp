 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>
    
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Prueba de MVC</title>
    </head>
    <body>
       
            <a href="/aaaa/mvc/view/loginView.jsp">Iniciar Sesion</a>
            <a href="/aaaa/mvc/controller/Registro.jsp">Registrase</a>
            

        

    </body>
</html> 