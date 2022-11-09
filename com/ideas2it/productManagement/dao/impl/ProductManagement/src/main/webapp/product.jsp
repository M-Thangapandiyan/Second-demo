<%@page import="com.ideas2it.productManagement.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<title>Product Menu</title>
</head>
<body>

	<div class="container">
			    <li><a href="createProduct.jsp"><button
							class="btn btn-success btn-sm">CreateProduct</button></a><br></li>
				<hr>
			<form method="get" action="getProducts">
				    <li><a href="getProducts"><button
							class="btn btn-success btn-sm">Get Products</button></a><br></li>
					<hr>
									<button type="submit">Submit</td>
			</form>

				<li><a href="getProductById.jsp"><button
							class="btn btn-success btn-sm">GetProductById</button></a><br></li>
				<hr>
				<li><a href="deleteProductById.jsp"><button
							class="btn btn-success btn-sm">DeleteProductById</button></a><br></li>
				<hr>
				<li><a href="updateProductById.jsp"><button
							class="btn btn-success btn-sm">UpdateProductById</button></a><br></li>
				<hr>
				<li><a href="searchProduct.jsp"><button
							class="btn btn-success btn-sm">SearchProduct</button></a> <br></li>
					<hr>
				<li><a href="getProductBetweenDate.jsp"><button
							class="btn btn-success btn-sm">GetProductBetweenDate</button></a></li>
				<hr> <br>
				<li><a href="getChoice.jsp"><button
							class="btn btn-success btn-sm">GetProductByIds</button></a></li>
				<hr> <br>
				
										<%
	List<Product> products = (List<Product>) session.getAttribute("products");
	%>
	<%= products %>
	</div>
</body>
</html>