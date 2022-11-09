<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="deleteManufacturerById">
		Enter the productId: <input type="number" name="id">
		<table>
			<tr>
				<td><button type="submit">Submit</td>
			</tr>
		</table>
	</form>
	<%
	if (null != session.getAttribute("isDeleted")) {
	%>
	<%
	boolean isDeleted = (boolean) session.getAttribute("isDeleted");
	%>
	<%
	if (isDeleted) {
	%>
	<%=" Manufacturer is deleted "%>
	<%
	}
	}
	%>
</body>