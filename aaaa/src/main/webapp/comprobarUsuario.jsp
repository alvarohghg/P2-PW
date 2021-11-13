<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario,
	 java.time.LocalDate, java.util.Date, java.sql.*, java.util.* " %>


	<% String nombre = request.getParameter("nombre"); %>

	<% String apellidos = request.getParameter("apellidos"); %>

	<% String nick = request.getParameter("nick"); %>

	<% String correo = request.getParameter("correo"); %>

	<% String tipo = request.getParameter("tipo"); %>
	<% int op; %>
	<% 	 Date date = new Date();
   		 long timeInMilliSeconds = date.getTime();
		java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds) ; %>
	
	<% UsuarioDAO UDAO=new UsuarioDAO(); %>
	<% Usuario user = new Usuario(); %>
	<% GestorUsuario GU =new GestorUsuario(); %>
	<%	GU.guardarBDU();
		if(GU.existeUsuario(correo)) {
			op=0; 	
		}else {
			user.setNombre(nombre);
			user.setApellidos(apellidos);
			user.setNick(nick);
			user.setCorreo(correo);
			user.setTipo(tipo);
			user.setRegistro(fecha);
			user.setUltima(null);
			UDAO.escribirUsuarioBD(user);%>
			<%op=1;
		}
	%>
	    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Comprobar Usuario</title>
		<style>
			button {
			  background-color: #bd7df280;
			  border: none;
			  color: black;
			  padding: 20px;
			  text-align: center;
			  text-decoration: none;
			  display: inline-block;
			  font-size: 16px;
			  margin: 4px 2px;
			  cursor: pointer;
			}
			
			.button1 {
				border-radius: 8px;
			}
			body {
			  background-image: url(" https://i.imgur.com/l4nKkCG.png ");
			  background-repeat: no-repeat;
			  background-attachment: fixed; 
			   
			  background-size: cover;
			}
		</style>
	</head>
	<body>
		<% if(op==0){%>
			 <h1>Este correo ya está usado</h1>
				
				<a href="/aaaa/mvc/controller/Registro.jsp">
				    <button class="button1" type="button">Reintentar</button>
				</a>
		<%}
		else{%>
			 <h1>Usuario registrado con existo</h1>
				
				<a href="/aaaa/index.jsp">
				    <button class="button1" type="button">Continuar</button>
				</a>
		<%}
		
		%>
		
		
	</body>
</html>




