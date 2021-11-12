<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro</title>
		<style>
			form {
				background-color: black;
				color:  red;
				text-align: center;
			}
		</style>

	</head>
	<body>


		<form method="post" action= "../../comprobarUsuario.jsp" >
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
� 			<input type="radio" id="tipo" name="tipo" value="0">
� 			<label for="Admin">Admin</label><br>
� 			<input type="radio" id="tipo" name="tipo" value="1" checked>
� 			<label for="Espectador">Espectador</label><br><br>
			<input type="submit" value="Registrarse">
		</form>

	</body>
</html>