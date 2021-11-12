<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario,
	 java.time.LocalDate, java.util.Date, java.sql.*, java.util.* " %>
	 
	 <% String correo = request.getParameter("correo");%>
	 <%	UsuarioDAO UDAO=new UsuarioDAO();
		Usuario user = new Usuario();
		GestorUsuario GU =new GestorUsuario();
		GU.guardarBDU();
		Date registro = GU.fechaRegistro(correo);
		
		Date date = new Date(System.currentTimeMillis()) ;
		long timeInMilliSeconds = date.getTime();
		java.sql.Date fecha = new java.sql.Date(timeInMilliSeconds) ;
		%>
	 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Eres espectador</title>
	</head>
	<body>
		<h2>Actividad:</h2>
		<p >Fecha actual: <p style="font-family:Monospace, Times, serif"><%=fecha%></p>
		<p >Te registrastes un: <p style="font-family:Monospace, Times, serif"><%=registro%></p>
		<form methon="post" action= "/aaaa/modificarDatos.jsp">
			  <button type=submit value=<%=correo%> id="correo" name="correo">Modificar datos</button>
		</form>
		<br>
          <a style="text-align:left" href="/aaaa/index.jsp">
				    <button type="button">Desconectar</button>
			</a>
	</body>
</html>