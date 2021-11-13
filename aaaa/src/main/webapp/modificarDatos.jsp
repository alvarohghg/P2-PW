<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario,
 java.time.LocalDate, java.util.Date, java.sql.*, java.util.* " %>
 <% String correo = request.getParameter("correo"); %>   
    
    
 <!DOCTYPE html>
<html>
	<head>
	<style> 
	input[type=text]{
	  width: 100%;
	  padding: 12px 20px;
	  margin: 8px 0;
	  box-sizing: border-box;
	  border: none;
	  background-color: #bd7df280;
	  color: black;
	}
	form,h2 {
	 text-align:center;
	}
	input[type=submit]{
	background-color: #AA5C88;
	color:black;
	
	}
	
	</style>
	</head>
	<body>
		
		<h2>Modifique sus datos</h2>
		<p >Su correo actual es: <p style="font-family:Monospace, Times, serif"><%=correo%></p>
		
		<form method="post" action="/aaaa/guardarDatos.jsp">
		  <input type="hidden" id="correo" name="correo" value=<%=correo %>>		 
		  <label for="nick" >Nuevo nick</label>
		  <input type="text" id="nick" name="nick" value="">
		  <label for="nombre">Nuevo nombre</label>
		  <input type="text" id="nombre" name="nombre" value="">
		  <label for="apellidos">Nuevos apellidos</label>
		  <input type="text" id="apellidos" name="apellidos" value="">
		  <div style="text-align:right">
		      <input type="submit" value="Modificar" >		      
		  </div>
		</form>
	
	</body>
</html> 
