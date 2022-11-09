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
	<form method="post" action="getProductById">
		Enter the productId: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	Product product = (Product) session.getAttribute("product");
	%>
	<%
	if (null != product) {
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
			<td>Date</td>
			<td><%= product.getDate()%></td>
		</tr>

		<tr>
			<td>Color</td>
			<td><%=product.getColour()%></td>
		</tr>

		<tr>
			<td>LifeTime</td>
			<td><%=product.getLifeTime(product.getDate())%></td>
		</tr>

		<tr>
			<td>Dealer</td>
			<td><%=product.getDealer()%></td>
		</tr>

		<tr>
			<td>Manufacturer</td>
			<td><%=product.getManufacturer()%></td>
		</tr>
	</table>
	<%
	}
	%>
</body>
</html>