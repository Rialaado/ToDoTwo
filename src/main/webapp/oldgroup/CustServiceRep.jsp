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
	<script src="cookielib.js" type="text/javascript"></script>
	
	
	
	
</head>

<body id= "backgBody" onload="hideDates()">
	<div id="containter">

		<div id="banner">

			<h1><font face="verdana" color="white" style = "vertical-align: text-bottom;">LTE Call Failure Lookup</font></h1>
			<h2><font face="verdana" color="white" style = "vertical-align: text-bottom;">Customer Service</font></h2>

		</div>
			<div id="sidebar">
			<div id = "signedIn" style ="padding-left: 5px;">
				<p id="user" style = "font-size:20px;">User:</p>
				<p id="role">Role:</p>
				<br>
				<form style ="padding-left: 10px;">
				<input type="button" name="Logout" value= "Logout" style = "width:160px; height:25px; align:center;" onclick = "logout();">
				</form>
			</div>
			<hr>
		
			
			
			<h1 id="filter">Please Choose a Query</h1>
			<hr>
			<div class="options">
				<form action="" method=GET  style =" padding-left: 10px; align:left;">
					
					<select style= "margin-bottom:7px;" id="CustSelection" name="CustSelection" onchange="if(this.value==3) {showDates();} else hideDates();">
						<option value="Event ID & Cause Codes">Event ID & Cause Codes</option>
						<option value="All Unique Cause Codes">All Unique Cause Codes</option>
						<option value="3">Count failures for fixed period</option>						
					</select>

					<input type="text" id="imsiSearch" name="imsiSearch" placeholder="Enter IMSI..." style = "width:205px;"/> 	

					<label id ="startDateL" for = "startDate" style= "text-align: left; display:none;">Start Date:</label>
					<input type= "datetime-local" id="startDate" name="startDate" style="display: none; width:175px; height:25px; align:center;">
					<label id="endDateL" for = "endDate" style= "text-align: left; display:none;">End Date:</label>
					<input type= "datetime-local" id="endDate" name="endDate" style="display: none; width:175px; height:25px; align:center;">
					<br> <input style = "width:160px; height:25px; align:center;" type="button" name="action" value="Search IMSI" onclick="lookupImsiEventCallFailure(); " >
				</form>
			</div>
			
			<hr>
			<h1  id="filter2" style = "font-size: 20px; display:none;">Move Page</h1>
			<hr id = "optionHr" style = "display:none;">
			
			<div id="navigate" style= "padding-left: 10px;">
				<table>
				<tr><td> <button  style = "width:160px; height:25px; align:center;" class="nav" id="suppNav" value="Support" onclick="location.href='/RestJPA/supportEngineer.jsp'"> Support </button></td></tr>
				<tr><td> <button  style = "width:160px; height:25px; align:center;"class="nav" id="NetMgmtNav"  value="Network Mgmt" onclick="location.href='/RestJPA/NetMgmtEng.jsp'"> Network Mgmt</button></td></tr>
				<tr> <td><button  style = "width:160px; height:25px; align:center;"class="nav" id="sysAdminNav" value="System Admin" onclick="location.href='/RestJPA/sysAdmin.jsp'"> System Admin </button></td></tr>
				</table>
			
			</div>	
		</div>
		<div class="output"></div>
			<div id="summary">
				<p id="cus1"></p>
				<p id="cus2"></p>
				<p id="cus3"></p>
			</div>
		</div>
		<div class="spacer"></div>
		<div id="results">
			<div class="CSSTableGenerator">
					<table  id="tableid" cellspacing="0" cellpadding="1" border="1" align="center"> 	
					</table>
			</div>	
		</div>
		<script type="text/javascript">

	var userLevel = null;
	window.onload = doStuff();
	
	
	function doStuff(){
	userLevel = getCookie("loginservice3");
	userName = getCookie("loginservice1");
	var roleName = getRoleName(userLevel);
	
	document.getElementById("user").innerHTML = "User: "+userName;
	document.getElementById("role").innerHTML = "Role: "+roleName;
	
	setButtons(userLevel);
	}


	function getRoleName(role){
		var roleName=null;
		
		if(role=="SysAdmin"){
			roleName = "System Administrator";
		}
			
		if(role=="NetEng"){
			
			roleName = "Network Engineer";
			}

		if(role=="SupEng"){
			roleName = "Support Engineer";
		}

		if(role=="CustRep"){
			roleName = "Customer Service Representative";
		}

		return roleName;
		
		}

	function showHeaderForButtons()
	{
		document.getElementById("optionHr").style.display="block";
		document.getElementById("filter2").style.display="block";
		
	}
	

	function setButtons(level){
	
		

		if(level=="SysAdmin"){
			document.getElementById("suppNav").style.display="inline";
			document.getElementById("NetMgmtNav").style.display="inline";
			document.getElementById("sysAdminNav").style.display="inline";
			showHeaderForButtons();
			}

		if(level=="NetEng"){
			document.getElementById("suppNav").style.display="inline";
			document.getElementById("NetMgmtNav").style.display="inline";
			showHeaderForButtons();
			
			}
		if(level=="SupEng"){
			document.getElementById("suppNav").style.display="inline";
			showHeaderForButtons();
			}		
		}


	function logout(){
		deleteCookie("loginservice1");
		deleteCookie("loginservice2");
		deleteCookie("loginservice3");
		window.location.assign("http://localhost:8080/RestJPA/login.html"); 
	}
	



function deleteAllRows() {

	var myTable = document.getElementById("tableid");
	var rowCount = myTable.rows.length;

	for (var i = 0; i < rowCount; i++) {
		myTable.deleteRow(0);

	}
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
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;


	var validate;
	
	validate = checkImsiField(imsiNumber);
	if(validate == false){
		return false;
	}

	
	if(custSelection == "Event ID & Cause Codes"){
		addUrl = "/Event ID & Cause Codes/"+ imsiNumber+ "/null"+ "/null";
	}else if(custSelection == "All Unique Cause Codes"){
		addUrl = "/All Unique Cause Codes/" + imsiNumber+ "/null"+ "/null";
	}else if(custSelection == "3"){

		validate = checkDateField(startDate, endDate);
		if(validate == false){
			return false;
		}
		addUrl = "/3/" + imsiNumber + "/" + startDate + "/" + endDate;
	}
    
    getRequest( { 
        	url: "http://localhost:8080/RestJPA/services/message/customerservicerep" + addUrl,
            payload: null,
            handler: function(response) { 
            	var para1 =document.getElementById("cus1");
            	var para2 =document.getElementById("cus2");
            	var para3 =document.getElementById("cus3");
            	para1.style.color = "black";
		    	para2.style.color = "black";
		    	para3.style.color = "black";
		    	
            	JSON_object = JSON.parse(response);
            	deleteAllRows();

				if(custSelection == "3"){
					poputateCount(JSON_object.data);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
				}else{
					poputateImsiDetails(JSON_object.data);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
				}
				
               }
      		} );
 }

function poputateCount(jsonObj){
	var table = document.getElementById("tableid");
	var row = table.insertRow(0);
	var cell1 = row.insertCell(0);
	cell1.innerHTML = "Number of failures";

	row = table.insertRow(1);
	cell1 = row.insertCell(0);
	cell1.innerHTML = jsonObj.length;
}

function poputateImsiDetails(jsonObj){
	var table = document.getElementById("tableid");
	var row = table.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1); 
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);

	cell1.innerHTML = "Date/Time";
	cell2.innerHTML = "IMSI";
	cell3.innerHTML = "EventID";  
	cell4.innerHTML = "Cause Code";


	for(var i = 0; i < jsonObj.length; i++){
		row = table.insertRow(i+1);
		cell1 = row.insertCell(0);
		cell2 = row.insertCell(1); 
		cell3 = row.insertCell(2);
		cell4 = row.insertCell(3);
		var webDate = new Date(jsonObj[i].dateTime);
		var dateString = webDate.toString();
		var finalDate = dateString.substring(0,24);
		cell1.innerHTML = finalDate;
		cell2.innerHTML = jsonObj[i].imsi;
		cell3.innerHTML = jsonObj[i].eventdetail.id.eventID;
		cell4.innerHTML = jsonObj[i].eventdetail.id.causeCode;
	}       
}
	
function getRequest(request) {
    var http_request  = new XMLHttpRequest();
    http_request.open("GET", request.url, true);
    http_request.setRequestHeader("Content-type","application/json");
    http_request.onreadystatechange = function() {
        if (http_request.readyState == 4 && http_request.status == 200) {
            request.handler.call(request, http_request.responseText);
        }else{
        	deleteAllRows();
        	var para1 =document.getElementById("cus1");
        	var para2 =document.getElementById("cus2");
        	var para3 =document.getElementById("cus3");
        	var JSON_object = JSON.parse(http_request.responseText);
	    	para1.style.color = "red";
	    	para2.style.color = "red";
	    	para1.innerHTML = JSON_object.notification;
	    	para2.innerHTML = JSON_object.notification1;
        }
    };
    http_request.send( request.payload );
}


function checkImsiField(imsiNumber){
	var bool = isNaN(imsiNumber);

	var imsi = imsiNumber.trim();
	var numbers = /^[0-9]+$/;  
    if(!imsiNumber.match(numbers) || bool == true || imsi.length !=  15){
        alert('Please Enter Valid IMSI Number');
        return false;
    }

    return true;
}

function checkDateField(startdatetime, enddatetime){

	 if(startdatetime == 0 || enddatetime == 0 || startdatetime > enddatetime){
		 alert('Please Enter Valid DateTime');
	     return false;
	 }
	 return true;
}
</script>
</body>
</html>
