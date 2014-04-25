<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="d" uri="http://java.sun.com/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.io.output.*"%>

<!DOCTYPE html>



<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<title>Customer Service</title>

<link href="style.css" rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript">
	function deleteAllRows() {

		var myTable = document.getElementById("tableid");
		var rowCount = myTable.rows.length;
		for (var x = rowCount; x > 0; x--) {
			myTable.deleteRow(x);
		}
	}

	function addHeader1() {
		var table = document.getElementById("tableid");
		var header = table.createTHead();
		var row = header.insertRow(0);

		var cell = row.insertCell(0);
		cell.innerHTML = "<br> Date/Time </br>";

		var cell = row.insertCell(1);
		cell.innerHTML = "<br> IMSI </br>";

		var cell = row.insertCell(2);
		cell.innerHTML = "<br> EventID </br>";

		var cell = row.insertCell(3);
		cell.innerHTML = "<br> Cause Code </br>";
	}

	function showDates() {
		document.getElementById("startDateL").style.display = "block";
		document.getElementById("startDate").style.display = "block";
		document.getElementById("endDateL").style.display = "block";
		document.getElementById("endDate").style.display = "block";

	}
	function hideDates() {
		document.getElementById("startDateL").style.display = "none";
		document.getElementById("startDate").style.display = "none";
		document.getElementById("endDateL").style.display = "none";
		document.getElementById("endDate").style.display = "none";

	}


function lookupImsiEventCallFailure() {

	var JSON_object;
	
	var addUrl = "";
	var custSelection = document.getElementById("CustSelection").value;
	var imsiNumber = document.getElementById("imsiSearch").value;

	if(custSelection == "Event ID & Cause Codes"){
		addUrl = "";
	}else if(custSelection == "All Unique Cause Codes"){
		addUrl = "";
	}else if(custSelection == "3"){
		addUrl = "";
	}
    
    getRequest( { 
        	url: "http://localhost:8080/RestJPA/services/message/all",
            payload: null,
            handler: function(response) { 
            	alert("completed");
            	JSON_object = JSON.parse(response);
            	deleteAllRows();
				
				var table = document.getElementById("tableid");
				var row = table.insertRow(0);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1); 
				var cell3 = row.insertCell(2);
				cell1.innerHTML = "ID";
				cell2.innerHTML = "BODY";
				cell3.innerHTML = "TITTLE";          	
				for(var i = 0; i < JSON_object.length; i++){
					row = table.insertRow(i+1);
					cell1 = row.insertCell(0);
					cell2 = row.insertCell(1); 
					cell3 = row.insertCell(2);
					cell1.innerHTML = JSON_object[i].id;
					cell2.innerHTML = JSON_object[i].body;
					cell3.innerHTML = JSON_object[i].title;
				}         	
                //document.getElementById("result").innerHTML = JSON.parse(response).result; 
               }
      		} );
 }


	
function getRequest(request) {
    var http_request  = new XMLHttpRequest();
    http_request.open("GET", request.url, true);
    http_request.setRequestHeader("Content-type","application/json");
    http_request.onreadystatechange = function() {
        if (http_request.readyState == 4) {
        	
            request.handler.call(request, http_request.responseText);
        }
    };
    http_request.send( request.payload );
}

</script>

</head>

<body id= "backgBody" onload="hideDates()">
	<div id="containter">

		<div id="banner">

			<!--img class="logo" src="elogo.png"-->
			<h1><font face="verdana" color="white" style = "vertical-align: text-bottom;">LTE Call Failure Lookup</font></h1>

		</div>

		<div id="sidebar">

			<div id="signedIn" style ="padding-left: 10px;">
				<p id="user">User: ${username} Customer Service Rep</p>
				<br>
				<form action="LoginRegServlet"  method ="GET">
				<input type="submit" name="Logout" value= "logout" style = "width:90px; height:25px; align:center;" >
				</form>
			</div>
			<hr>
			<p id="notify">Status: Search for IMSI</p>			
			
			
			<h1 id="filter">Options: IMSI Query</h1>
			<hr>
			<br>
			<div class="options">
				<form action="" method=GET  style =" padding-left: 10px; align:left;">
					<label for="imsiSearch" style= "text-align: left; display:inline-block;">Enter IMSI:</label> 
					<input type="text" id="imsiSearch" name="imsiSearch" style = "width:205px;"/> 
					<select id="CustSelection" name="CustSelection" onchange="if(this.value==3) {showDates();} else hideDates();">
						<option value="Event ID & Cause Codes">Event ID & Cause Codes</option>
						<option value="All Unique Cause Codes">All Unique Cause Codes</option>
						<option value="3">Count failures for fixed period</option>
												
					</select> 

					<label id ="startDateL" for = "startDate" style= "text-align: left; display:inline-block;">Start Date:</label>
					<input type= "datetime-local" id="startDate" name="startDate" style="display: none;">
					<label id="endDateL" for = "endDate" style= "text-align: left; display:inline-block;">End Date:</label>
					<input type= "datetime-local" id="endDate" name="endDate" style="display: none;">
					<br> <input type="button" name="action" value="Search IMSI" onclick="lookupImsiEventCallFailure();" >
				</form>
			</div>
			<hr>

		</div>
		
		<div class="spacer"></div>
		<div id="results">
			<div class="CSSTableGenerator">
					<table  id="tableid" cellspacing="0" cellpadding="1" border="1" align="center">
						<c:choose>
							<c:when test="${param.CustSelection == 'Event ID & Cause Codes'}">
								<c:forEach items="${IMSIeventcausecode}" var="events"
									varStatus="IndexOf">
									<c:if test="${IndexOf.index == 0}">
									<tr>
										<td>Date/Time</td>
										<td>IMSI</td>
										<td>EventID</td>
										<td>Cause Code</td>
									</tr>
								</c:if>
								<!--  class="hovertable" onmouseover="this.style.backgroundColor='#ffff66';"
										onmouseout="this.style.backgroundColor='#d4e3e5';"
										-->
									<tr >
										<td>${events.dateTime}</td>
										<td>${events.imsi}</td>
										<td>${events.eventdetail.id.eventID}</td>
										<td>${events.eventdetail.id.causeCode}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${param.CustSelection == 'All Unique Cause Codes'}">
								<c:forEach items="${IMSIeventcausecode}" var="events" varStatus="IndexOf">
									<c:if test="${IndexOf.index == 0}">
									<tr>
										<td>Date/Time</td>
										<td>IMSI</td>
										<td>EventID</td>
										<td>Cause Code</td>
									</tr>
								</c:if>
									<tr>
										<td>${events.dateTime}</td>
										<td>${events.imsi}</td>
										<td>${events.eventdetail.id.eventID}</td>
										<td>${events.eventdetail.id.causeCode}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${param.CustSelection == '3'}">
									<tr>
										<td>Number of failures</td>
									</tr>
									<tr>
										<td>${IMSIeventcausecode[0]}</td>
									</tr>
							</c:when>
						</c:choose>
					</table>
			</div>	
		</div>
			<div class="output"></div>
			<div id="summary">
				<!--  <p>${fn:length(resultsFound)} Records loaded successfully</p>-->
			</div>
		</div>
</body>

</html>
