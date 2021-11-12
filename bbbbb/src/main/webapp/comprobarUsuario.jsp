<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>


	<% String nombre = request.getParameter("nombre"); %>

	<% String apellidos = request.getParameter("apellidos"); %>

	<% String nick = request.getParameter("nick"); %>

	<% String correo = request.getParameter("correo"); %>

	<% String tipo = request.getParameter("tipo"); %>
	
	<% UsuarioDAO UDAO=new UsuarioDAO(); %>
	<% Usuario user = new Usuario(); %>
	<% GestorUsuario GU =new GestorUsuario(); %>
	<%	user.setNombre(nombre);
		user.setApellidos(apellidos);
		user.setNick(nick);
		user.setCorreo(correo);
		user.setTipo(tipo);
		if(tipo.equals("0")==true){
			
		}
		else{
			
		}
		System.out.print(user);
		//user.setFecha("fundsion de la fecha"); 
	%>
	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>