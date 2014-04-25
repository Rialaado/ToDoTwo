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
<html xmlns="http://www.w3.org/1999/xhtml">
<head bgcolor="000000" text=white>


	<title>To-Do List</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="style.css" rel="stylesheet" type="text/css" media="all" /> 
<script type="text/javascript">

function setUpDisplay()
{
	document.getElementById('usernameId').focus()	
}

function validateForm(){
		
	document.body.getElementById("loginFailed").value = "Please Enter a Valid Username or Password";	
	
}
	
function login() {

	    var username = document.getElementById("usernameId").value;
	    var password = document.getElementById("passwordId").value;
	    
	    postRequest( { 
	        	url: "http://localhost:8080/toDoList/services/message/login",
	            payload: JSON.stringify({username: username, password: password }),
	            handler: function(response) { 
	            	document.getElementById("loginStatus").style.visibility="hidden";
	 
	            	var JSON_object = JSON.parse(response);
	            	setCookie("user", JSON_object.username, 0)
	            	setCookie("password", JSON_object.password, 0)
	           
	            	window.location.assign(JSON_object.url); 
	               }
	      		 } );
 }


function postRequest( request ) {
    var http_request  = new XMLHttpRequest();
    http_request.open("POST", request.url, true);
    http_request.setRequestHeader("Content-type","application/json");
    http_request.onreadystatechange = function() {
    	var notification = document.getElementById("loginStatus");
    	
        if (http_request.readyState == 4 && http_request.status == 200) {
        	notification.style.visibility="hidden";
        	document.getElementById("login").reset();
            request.handler.call(request, http_request.responseText);
            
        }else{
        	var JSON_object = JSON.parse(http_request.responseText);
        	notification.style.visibility = "visible";
        	notification.innerHTML =  JSON_object.notification;
        	notification.style.color = "red";
        }
    }
    http_request.send( request.payload );
}

</script>

<body>

	<div class="background">
		<div class="banner">

		</div>
	
	<div class="middle">
	<form action="" class=loginform id="login" method=GET>
 
	<h1> Login </h1>
	<ul>
            <li>
                <label for="username">username</label>
                <input type="text" name="user" required="" id= "usernameId">
            </li>
            <li>
                <label for="password">password</label>
                <input type="text" name="password" required="" id= "passwordId">
            </li>
            <li>
                <input type="button" name = "signin" value="Login" id="input3" onclick = "login();">
		<a href="register.html"> Register</a>
            </li>
    
        </ul>
			
	</form>
	<div id = "loginStatus" align="center">
	<br>
	</div>
		
	</div>
			<script src="cookielib.js" type="text/javascript"></script>
</body>
</html></html>
