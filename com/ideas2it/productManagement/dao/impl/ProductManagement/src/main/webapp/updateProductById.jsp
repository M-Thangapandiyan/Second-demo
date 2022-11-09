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
	<form method="get" action="getProduct">
		Enter the update product id: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	Product product = (Product) session.getAttribute("product");
	%>
	<form method="post" action="updateProductById">
		<table>
			<%
			if (null != product) {
			%>
			<tr>
				<td>id</td>
				<td><input type="number" name="id"
					value="<%=product.getId()%>"></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"
					value="<%=product.getName()%>"></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="number" name="price"
					value=<%=product.getPrice()%>></td>
			</tr>
			<tr>
				<td>DOB</td>
				<td><input type="date" name="dob"
					value=<%=product.getDate()%>></td>
			</tr>
	
			<tr>
                <td>colour</td>
                <% String option = product.getColour().toString(); %>   
                <% option = product.getColour().toString(); %>
                <td> 
                    <input type ="radio" name ="colour" value = "BLUE"
                    <% if(option.equals("BLUE")) {%> <%= "checked" %> <% }%> >Blue
                    <input type ="radio" name ="colour" value = "WHITE"
                    <% if(option.equals("GREEN")) {%> <%= "checked" %> <% }%> >Green
                    <input type ="radio" name ="colour" value = "RED"
                    <% if(option.equals("RED")) {%> <%= "checked" %> <% }%> >Red
                    <input type ="radio" name ="colour" value = "YELLOW"
                    <% if(option.equals("YELLOW")) {%> <%= "checked" %> <% }%> >Yellow
                </td>
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