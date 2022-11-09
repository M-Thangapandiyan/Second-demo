<%@page import="com.ideas2it.productManagement.model.Product"%>
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
<form method="get" action="searchProduct">
		Enter the product name: <input type="text" name="name">
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