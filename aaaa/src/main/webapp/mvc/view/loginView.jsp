<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Iniciar sesion</title>
		<style>
			form,h2 {
				 text-align:center;
			}
			input[type=text]{

			  padding: 12px 20px;
			  margin: 8px 0;
			  box-sizing: border-box;
			  border: none;
			  background-color: #bd7df280;
			  color: black;	
			}
			input{
				background-color: #bd7df280;
			}
			input[type=submit]{
				background-color: #AA5C88;
				color:black;
			}
		</style>

	</head>
	<body>
			<form method="post" action="../controller/loginController.jsp">
				<label for="name">Nick: </label>
				<input type="text" name="nick" value="" id="nick"><br/>
				<label for="email">Correo: </label>
				<input type="text" name="correo" value="" id="correo">	
				<br/>
				<input type="submit" value="Iniciar">	
			</form>
	</body>
</html>





