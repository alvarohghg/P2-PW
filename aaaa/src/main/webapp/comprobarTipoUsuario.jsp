<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.Date,java.util.ArrayList,es.uco.pw.data.common.DBConnection,es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<% String tipo = request.getParameter("tipo"); %>
<% String correo = request.getParameter("correo"); %>
<% ArrayList<Usuario>listausuarios=new ArrayList<Usuario>();%>
<%GestorUsuario GU = new GestorUsuario(listausuarios);%>
<%UsuarioDAO UDAO=new UsuarioDAO();%>
<%java.util.Date fecha = new Date(); %>
	
<% 
if(tipo.equals("0")==true){
			
			for(int i=0; i< GU.devolverLista().size(); i++) {
				
				out.println("User["+i+"]: "+GU.devolverLista().get(i).getNick());
				out.println("Tipo: "+GU.devolverLista().get(i).getTipo());
				}
			
			for(int i=0; i< GU.devolverLista().size(); i++) {
				if(GU.devolverLista().get(i).getCorreo().equals(correo)){
					out.println("Ultimo inicio de sesión:"+GU.devolverLista().get(i).getUltimaConexion());
				}
			}
		}
		else{
			for(int i=0; i< GU.devolverLista().size(); i++) {
				out.println ("Fecha actual: "+fecha);
				if(GU.devolverLista().get(i).getCorreo().equals(correo)){
					out.println("Fecha de registro:"+GU.devolverLista().get(i).getFechaRegistro());
				}
			}
			
		}
%>


</body>
</html>
