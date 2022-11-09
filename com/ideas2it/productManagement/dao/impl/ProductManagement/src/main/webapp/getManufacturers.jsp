<%@page import="com.ideas2it.productManagement.model.Manufacturer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	List<Manufacturer> manufacturers = (List<Manufacturer>) session.getAttribute("manufacturers");
	%>
	<%
	if (null != manufacturers) {
	%>
	<%
	for (Manufacturer manufacturer : manufacturers) {
	%>
	<table>

		<tr>
			<td>Brand</td>
			<td><%=manufacturer.getBrand()%></td>
		</tr>
		<tr>
			<td>Place</td>
			<td><%=manufacturer.getPlace()%></td>
		</tr>
	</table>
	<%
	}
	}
	%>
</body>
</html>