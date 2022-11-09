<%@page import="com.ideas2it.productManagement.model.Dealer"%>
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
	List<Dealer> dealers = (List<Dealer>) session.getAttribute("dealers");
	%>
	<%
	if (null != dealers) {
	%>
	<%
	for (Dealer dealer : dealers) {
	%>
	<table>

		<tr>
			<td>Company</td>
			<td><%=dealer.getCompany()%></td>
		</tr>

		<tr>
			<td>Location</td>
			<td><%=dealer.getLocation()%></td>
		</tr>
	</table>
	<%
	}
	}
	%>
</body>
</html>