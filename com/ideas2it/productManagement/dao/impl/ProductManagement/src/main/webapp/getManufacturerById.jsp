<%@page import="com.ideas2it.productManagement.model.Manufacturer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ManufacturerById</title>
</head>
<body>
	<form method="post" action="getManufacturerById">
		Enter the manufacturerId: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
	%>
	<%
	if (null != manufacturer) {
	%>
	<table>

		<tr>
			<td>id</td>
			<td><%=manufacturer.getId()%></td>
		</tr>

		<tr>
			<td>brand</td>
			<td><%=manufacturer.getBrand()%></td>
		</tr>

		<tr>
			<td>place</td>
			<td><%=manufacturer.getPlace()%></td>
		</tr>
	</table>
	<%
	}
	%>
</body>
</html>