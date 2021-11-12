<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Iniciar sesion</title>
	</head>
	<body>
			<form method="post" action="../controller/loginController.jsp">
				<label for="name">Nick: </label>
				<input type="text" name="nick" value="" id="nick"><br/>
				<label for="email">Correo: </label>
				<input type="text" name="correo" value="" id="correo">	
				<br/>
				<input type="submit" value="Submit">
			</form>
	</body>
</html>





