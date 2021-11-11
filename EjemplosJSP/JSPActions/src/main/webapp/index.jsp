<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example of includes</title>
</head>
<body>
<!-- Next include is performed at translation time -->
<%@ include file="header.jsp" %>

<% // We have 3 possible products, which are shown depending on a random ID
	int num = 1+(int)(Math.random() * 3);
%>

This is your product with ID <%= num %>: <br/><br/>
<jsp:include page="product.jsp"><jsp:param value="<%= num %>" name="productId"/></jsp:include>

</body>
</html>