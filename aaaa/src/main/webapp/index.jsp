 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>
    
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>Inicio</title>
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
			  background-image: url("https://i.imgur.com/l4nKkCG.png");
			  background-repeat: no-repeat;
			  background-attachment: fixed;  
			  background-size: cover;
			} 
			</style>
    </head>
    <body>
       		<div style="text-align:center">
			      <a href="/aaaa/mvc/view/loginView.jsp">
	       			<button class="button1">Iniciar sesion</button>
	           	 </a>	
	           	 <a href="/aaaa/mvc/controller/Registro.jsp">
       					<button class="button1">Registrarse</button>
					</a>	      
		 	 </div>
			<article>
				  <marquee SCROLLAMOUNT=2500 LOOP=1>
				            <b> <img src="https://i.imgur.com/NUyttbnb.jpg"> </font></b>
				   </marquee>
       
 			</article>
    </body>
</html> 
