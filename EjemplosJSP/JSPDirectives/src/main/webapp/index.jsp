<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="errorPage.jsp" %>

<%
// Getting the parameter of the http-request
String arg1=request.getParameter("arg1");  
String arg2=request.getParameter("arg2"); 

// Converting strings (params) to a (native) integer
int num1 = Integer.parseInt(arg1);
int num2 = Integer.parseInt(arg2);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>This page causes an error!</title>
</head>
<body>
The result of this function is <%= num1/num2 %>
</body>
</html>


<%--
Try to execute: 
	http://127.0.0.1:8080/directivas/index.jsp?arg1=324&arg2=47
and
	http://127.0.0.1:8080/directivas/index.jsp?arg1=324&arg2=0
--%>