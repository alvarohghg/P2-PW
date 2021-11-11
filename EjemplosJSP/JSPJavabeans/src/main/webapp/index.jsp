<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="es.uco.pw.display.common.Language" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Probando el funcionamiento de CustomerBean</title>
</head>
<body>
Recuerde que su CustomerBean tendr&aacute; un &aacute;mbito de sesi&oacute;n.<br/><br/>
<% // Usuario no logado
if (customerBean.getLogin()=="" || customerBean == null) {
%>
<a href="login.jsp">Acceder</a>
<% } else { %>
	Bienvenido <jsp:getProperty property="login" name="customerBean"/>!! 
	(id_<jsp:getProperty property="idUser" name="customerBean"/>)
	(<%if (customerBean.getUserLang() == Language.English) {%>EN
	<%} else if (customerBean.getUserLang() == Language.Polish) {%>PO
	<%} else if (customerBean.getUserLang() == Language.German) {%>GE
	<%} else { %>ES
	<% } %>)<br/><br/>
	<a href="logout.jsp">Desconectar</a>
<% } %>

</body>
</html>