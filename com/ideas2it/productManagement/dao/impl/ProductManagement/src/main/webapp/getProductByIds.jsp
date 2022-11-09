<%@page import="com.ideas2it.productManagement.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
int id = 0;
%>
<%
if (null != request.getParameter("id")) {
%>
<%
id = Integer.parseInt(request.getParameter("id"));
%>
<%
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Product Get By Id's</title>
</head>
<body>
	<form method="get" action="getProductByIds">
		<input name="choice" value="<%=id%>" readonly>
		<%
		for (int index = 0; index < id; index++) {
		%>
		<table>
			<tr>
				Enter the productId:
				<input type= "number" name="id-<%= index + 1 %>">
			</tr>
		</table>
		<%
		}
		%>
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	List<Product> products = (List<Product>) session.getAttribute("products");
	%>
	<%
	if (null != products) {
	%>
	<%
	for (Product product : products) {
	%>
	<table>
		<tr>
			<td>id</td>
			<td><%=product.getId()%></td>
		</tr>

		<tr>
			<td>Code</td>
			<td><%=product.getCode()%></td>
		</tr>

		<tr>
			<td>Name</td>
			<td><%=product.getName()%></td>
		</tr>

		<tr>
			<td>Price</td>
			<td><%=product.getPrice()%></td>
		</tr>

		<tr>
			<td>Colour</td>
			<td><%=product.getColour()%></td>
		</tr>

		<tr>
			<td>LifeTime</td>
			<td><%=product.getLifeTime(product.getDate())%></td>
		</tr>
	</table>
	<%
	}
	}
	%>
</body>
</html>