<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrarse</title>
	</head>
	<body>
		<form method="post" action= "../../index.jsp">
			<label for="nombre">Nombre: </label>
			<input type="text" name="nombre" value="" id="nombre"><br/><br/>
			<%// String nombre = request.getParameter("nombre"); %>
			<label for="apellidos">Apellidos: </label>
			<input type="text" name="apellidos" value="" id="apellidos"><br/><br/>
			<%//String apellidos = request.getParameter("apellidos"); %>
			<label for="nick">Nick: </label>
			<input type="text" name="nick" value="" id="nick"><br/><br/>	
			<% //String nick = request.getParameter("nick"); %>
			<label for="correo">Correo: </label>
			<input type="text" name="correo" value="" id="correo"><br/><br/>
			<%// String correo = request.getParameter("correo"); %>
			<label for="tipo">Tipo: [0]Admin [1]Espectador </label>
			<input type="text" name="tipo" value="" id="tipo"><br/><br/>
			<% //String tipo = request.getParameter("tipo"); %>
		
			<% //UsuarioDAO UDAO=new UsuarioDAO(); %>
			<% //Usuario user = new Usuario(); %>
			<% //GestorUsuario GU =new GestorUsuario(); %>
			<%	/*user.setNombre(nombre);
				user.setApellidos(apellidos);
				user.setNick(nick);
				user.setCorreo(correo);
				user.setTipo(tipo);
				System.out.print(user);*/
				//user.setFecha("fundsion de la fecha"); 
			%>
			
			<% 	//GU.addUser(user); %>
			<% //	UDAO.escribirUsuarioBD(user);%>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>