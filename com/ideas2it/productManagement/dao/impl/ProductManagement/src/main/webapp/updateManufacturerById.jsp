<%@page import="com.ideas2it.productManagement.model.Manufacturer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="getManufacturer">
		Enter the update manufacturer id: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	Manufacturer manufacturer = (Manufacturer) session.getAttribute("manufacturer");
	%>
	<form method="get" action="updateManufacturerById">
		<table>
			<%
			if (null != manufacturer) {
			%>
			<tr>
				<td>id</td>
				<td><input type="number" name="id"
					value="<%=manufacturer.getId()%>"></td>
			</tr>
			<tr>
				<td>Brand</td>
				<td><input type="text" name="brand"
					value="<%=manufacturer.getBrand()%>"></td>
			</tr>
			<tr>
				<td>Place</td>
				<td><input type="text" name="place"
					value="<%=manufacturer.getPlace()%>"></td>
			</tr>
		<tr>
				<td><button type="submit">Submit</button></td>
			</tr>
		</table>
	</form>
	<%
	if (null != session.getAttribute("isThere")) {
	%>
	<%
	boolean action = (boolean) session.getAttribute("isThere");
	%>
	<%
	if (action) {
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