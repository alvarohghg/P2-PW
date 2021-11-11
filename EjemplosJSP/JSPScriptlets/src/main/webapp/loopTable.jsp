<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tabla CON bucle</title>
</head>
<body>
<table><caption>Tabla CON bucle</caption>
<% for (int i = 1; i <= 5; i++) {%>
<tr><td>Valor</td><td><%= i %></td></tr>
<% } %>
</table>
</body>
</html>