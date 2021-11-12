<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrarse</title>
		<style>
			form {
			background-color: black;
			color:  red;
			text-align: center;
		}
	</style>

	</head>
	<body>


		<form method="post" action= "../../index.jsp" >
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
  			<input type="radio" id="Admin" name="TipoUsuario" value="0">
  			<label for="Admin">Admin</label><br>
  			<input type="radio" id="Espectador" name="TipoUsuario" value="1" checked>
  			<label for="Espectador">Espectador</label><br><br>

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
			<input type="submit" value="Registrarse">
		</form>

	</body>
</html>