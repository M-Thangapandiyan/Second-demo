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
	<form method="post" action="getDealerById">
		Enter the dealerId: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	Dealer dealer = (Dealer) session.getAttribute("dealer");
	%>
	<%
	if (null != dealer) {
	%>
	<table>

		<tr>
			<td>id</td>
			<td><%=dealer.getId()%></td>
		</tr>
		
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
	%>
</body>
</html>