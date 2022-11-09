<%@page import="com.ideas2it.productManagement.model.Dealer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="getDealer">
		Enter the update dealer id: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	Dealer dealer = (Dealer) session.getAttribute("dealer");
	%>
	<form method="get" action="updateDealerById">
		<table>
			<%
			if (null != dealer) {
			%>
			<tr>
			<tr>
				<td>id</td>
				<td><input type="number" name="id" value="<%=dealer.getId()%>"></td>
			</tr>
			<tr>
				<td>Company</td>
				<td><input type="text" name="company"
					value="<%=dealer.getCompany()%>"></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><input type="text" name="location"
					value="<%=dealer.getLocation()%>"></td>
			</tr>
			<tr>
				<td><button type="submit">Submit</button></td>
			</tr>
		</table>
	</form>
	<%
	if (null != session.getAttribute("found")) {
	%>
	<%
	boolean a = (boolean) session.getAttribute("found");
	%>
	<%
	if (a) {
	%>
	<%=" updated successfully"%>
	<%
	}
	}
	%>
	<%
	}
	%>
</body>
</html>