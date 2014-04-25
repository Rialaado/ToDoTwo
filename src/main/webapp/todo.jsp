<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="d" uri="http://java.sun.com/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="style.css" rel="stylesheet" type="text/css" media="all" /> 
<html>

	<script type="text/javascript">

	function logout(){
		deleteCookie("user");
		deleteCookie("password");
		window.location.assign("http://localhost:8080/toDoList/login.jsp"); 
	}



	</script>


<body>

	<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
		if(cookies !=null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user")) userName = cookie.getValue();
			}
		}
		if(userName == null) response.sendRedirect("login.jsp");
	%>



	<div class="background">

		<div class="banner">

		</div>
	
	<div class="middle">

	<h1> To Do: </h1>
	<form class=addItem action="AddItemServlet" method="get">
		<label for="item">add item:</label>
                <input type="text" name="item">
		<input type="submit" name="ItemAdd" value="Add">
	
	</form>	

	 		<table class="hovertable" cellspacing="0" cellpadding="1" border="1" align="center">
			<c:forEach items= "${things}" var = "errands">
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
			<td>${errands.itemName}</td>			
			</tr>
			</c:forEach>	
			</table>
			

		<p>${message}</p>

		<form class=logout >
		<input type="button" name="Logout" value="Logout" onclick = "logout();">
	
	</form>

	</div>
				<script src="cookielib.js" type="text/javascript"></script>
</body>
</html>

