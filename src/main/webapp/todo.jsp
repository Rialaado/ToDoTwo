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

	function populateToDoTable(jsonObj){
		var table = document.getElementById("tableid");
		var row = table.insertRow(0);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1); 
		var cell3 = row.insertCell(2); 
		var cell4 = row.insertCell(3);
	
		cell1.innerHTML = "No.";
		cell2.innerHTML = "Item";
		cell3.innerHTML = "Options";
		  
		
		for(var i = 0; i < jsonObj.events.length; i++){
			row = table.insertRow(i+1);
			cell1 = row.insertCell(0);
			cell2 = row.insertCell(1);
			cell3 = row.insertCell(2); 
		
			cell1.innerHTML = i+1;
			cell2.innerHTML = jsonObj.events[i].eventdetail.id.causeCode;
			cell3.innerHTML = "Edit/Remove";
		
		}       
	}

	function getToDoList() {
		var JSON_object;
	
		var addUrl = "";
		var accountName = getCookie("user");
		
		if(accountName != null){
			addUrl = accountName;
		}
				getRequest( { 
			    	url: "http://localhost:8080/RestJPA/services/message/pullList/" + addUrl,
			        payload: null,
			        handler: function(response) { 
				    	
			        	JSON_object = JSON.parse(response);
			        	//deleteAllRows();
						populateToDoTable(JSON_object.data); 
			           }
			  		} );
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

	
	function addItem(){

		var item = document.getElementById("addedItem").value;
		var username = getCookie("user");

		alert("info to be passed- item: "+item +" username: "+username);

		
			postRequest( { 
		    	url: "http://localhost:8080/toDoList/services/message/addItem",
		        payload: JSON.stringify({item: item, username:username }),
		        handler: function(response){ 
		        	JSON_object = JSON.parse(response);
		        	//deleteAllRows();
					//populateToDoTable(JSON_object.data); 
		           }
		  		} );
		}


	function postRequest( request ) 
	{
	    var http_request  = new XMLHttpRequest();
	    http_request.open("POST", request.url, true);
	    http_request.setRequestHeader("Content-type","application/json");
	    http_request.onreadystatechange = function() 
	    {
	    	var notification = document.getElementById("addedStatus");
	    	
	        if (http_request.readyState == 4 && http_request.status == 200) 
		        {
	        	notification.style.visibility="hidden";
	        	document.getElementById("itemAdd").reset();
	            request.handler.call(request, http_request.responseText);    
	        	}
	        else
		        {
	        	var JSON_object = JSON.parse(http_request.responseText);
	        	notification.style.visibility = "visible";
	        	notification.innerHTML =  JSON_object.notification;
	        	notification.style.color = "red";
	        	}
	    }
	    http_request.send( request.payload );
	}
	

	function getRequest(request) {
	    var http_request  = new XMLHttpRequest();
	    http_request.open("GET", request.url, true);
	    http_request.setRequestHeader("Content-type","application/json");
	    http_request.onreadystatechange = function() {
	        if (http_request.readyState == 4 && http_request.status == 200) {      	
	            request.handler.call(request, http_request.responseText);
	        }else{
	        	//deleteAllRows();
	        	var JSON_object = JSON.parse(http_request.responseText);
		    }
	    };
	    http_request.send( request.payload );
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
	<form class=addItem id="itemAdd" action="" method="GET">
		<label for="item">add item:</label>
                <input type="text" name="item" id="addedItem">
		<input type="button" name="ItemAdd" value="Add" onclick="addItem();">
	
	</form>	
	
			<table  class="tabla"  id="tableid" cellspacing="0" cellpadding="1" border="1" align="center">
			</table>
	
	

	 	<!-- 	<table class="hovertable" cellspacing="0" cellpadding="1" border="1" align="center">
			<c:forEach items= "${things}" var = "errands">
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
			<td>${errands.itemName}</td>			
			</tr>
			</c:forEach>	
			</table>  -->


			</form>
			<div id="addedStatus" align="center">
				<br>
			</div>

			<form class=logout >
		<input type="button" name="Logout" value="Logout" onclick = "logout();">
	
	</form>

	</div>
				<script src="cookielib.js" type="text/javascript"></script>
</body>
</html>

