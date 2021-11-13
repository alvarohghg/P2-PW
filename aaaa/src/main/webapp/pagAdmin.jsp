<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.user.Usuario, es.uco.pw.data.dao.UsuarioDAO, es.uco.pw.business.user.GestorUsuario,
	 java.time.LocalDate, java.util.Date, java.sql.*, java.util.* " %>
	 
	 <% String correo = request.getParameter("correo");
	%>
	 
	 <%	UsuarioDAO UDAO=new UsuarioDAO();
		Usuario user = new Usuario();
		GestorUsuario GU =new GestorUsuario();
		ArrayList<Usuario> ListaUsuarios = new ArrayList<Usuario>();
		ListaUsuarios=UDAO.obtenerUsuarios();
	%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Eres Admin</title>
		<style>
			table {
			  border-collapse: collapse;
			  width: 50%;
			}
			th, td {
			  text-align: left;
			  padding: 8px;
			}
			.button {
			  background-color: #bd7df280;
			  border: none;
			  color: black;
			  padding: 4px;
			  text-align: center;
			  text-decoration: none;
			  display: inline-block;
			  font-size: 13.3px;
			  margin: 1px 1px;
			  cursor: pointer;
			}
			body {
			  background-image: url(" https://i.imgur.com/l4nKkCG.png");
			  background-repeat: no-repeat;
			  background-attachment: fixed;  
			  background-size: cover;
			} 
			
			.button1 {
			 	
				text-align: center;
				border-radius: 8px;
			}
			tr:nth-child(odd) {background-color: #cc6699;}
			tr:nth-child(even) {background-color: #993366;}
			td:nth-child(4n){background-color:#48465d;
			
			}
			
			tr:hover {background-color: pink;}
		</style>
	</head>
	<body>
		<div align="center">
        <table border="0" cellpadding="5">
            <caption><h2 style="text-align:left">Lista de usuarios</h2></caption>
            <tr>
                <th>Nick</th>
                <th>Tipo</th>
                <th>Ultima conexion</th>
            </tr>
            <% for(int i=0; i< ListaUsuarios.size();i++){ 
            	String nick=ListaUsuarios.get(i).getNick();
                String tipo;
                if(ListaUsuarios.get(i).getTipo().equals("0")){
                	tipo="Administrador";
                }
                else{
                	tipo="Espectador";
                }
                Date fecha=ListaUsuarios.get(i).getUltimaConexion()	;
            %>
                <tr>
                    <td><%= nick %> </td>
                    <td><%= tipo %> </td>
                    <td><%= fecha %> </td>
                    <% if(correo.equals(ListaUsuarios.get(i).getCorreo())==true){ %>
	                    <td style="border:0px"> 
	                    <form methon="post" action= "/aaaa/modificarDatos.jsp">
			    			<button class="button button1" type=submit value=<%=ListaUsuarios.get(i).getCorreo()%> id="correo" name="correo">Modificar datos</button>
						</form>
	                    
		          	<%} %>
                </tr>
                
                    <%} %>
           
        </table>
       
        
    </div>
     <br>
          <a style="text-align:right" href="/aaaa/index.jsp">
				    <button class="button button1" type="button">Desconectar</button>
			</a>
	</body>
</html>
