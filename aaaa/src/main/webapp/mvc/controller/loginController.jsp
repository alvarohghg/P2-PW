 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario,
	 java.time.LocalDate, java.util.Date, java.sql.*, java.util.* " %>

<% 	String nick = request.getParameter("nick");
	String correo = request.getParameter("correo"); 
	UsuarioDAO UDAO=new UsuarioDAO();
	Usuario user = new Usuario();
	GestorUsuario GU =new GestorUsuario();
	int op;
	GU.guardarBDU();
	
	if(GU.existeNick(correo, nick)==true){
		GU.ponerFecha(correo);
		if(GU.esAdmim(correo)==true){
			op=0;
		}else{
			op=1;
		}
	}
	else{
		op=2;
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Comprobar inicio sesion</title>
	</head>
	<body>
		<% if(op==0){%>
			 <h1>Sesion inicia correctamente</h1>
				
				<form methon="post" action="/aaaa/pagAdmin.jsp">
			    	<button type=submit value=<%= correo %> id="correo" name="correo">Continuar</button>
				</form>
		<%}
		else if(op==1){%>
			<h1>Sesion inicia correctamente</h1>
			<form methon="post" action="/aaaa/pagEspec.jsp">
			    	<button type=submit value=<%= correo %> id="correo" name="correo">Continuar</button>

				</form>
		<%}
		else{%>
			 <h1>Usuario no registrado</h1>
				
				<a href="/aaaa/mvc/view/loginView.jsp">
				    <button type="button">Reintentar</button>
				</a>
				<a href="/aaaa/index.jsp">
				    <button type="button">Salir</button>
				</a>
		<%}
		
		%>
		
		
	</body>
</html>