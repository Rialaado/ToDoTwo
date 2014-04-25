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

	<title>Support Engineer</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="all" />

	<script type="text/javascript">
	
	
	var userLevel;

	window.onload=function() {
		userLevel = getCookie("loginservice3");
		userName = getCookie("loginservice1");
		document.getElementById("user").innerHTML = "User: "+userName;
		
		var roleName = getRoleName(userLevel);
	
		document.getElementById("role").innerHTML = "Role: "+roleName;
		setButtons(userLevel);
	};


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

	function logout(){
		deleteCookie("loginservice1");
		deleteCookie("loginservice2");
		deleteCookie("loginservice3");
		window.location.assign("http://localhost:8080/RestJPA/login.html"); 
	}

	function setButtons(level){

		if(level=="SysAdmin"){
			document.getElementById("NetMgmtNav").style.display="inline";
			document.getElementById("CustServNav").style.display="inline";
			document.getElementById("sysAdminNav").style.display="inline";

			
			}
		
		if(level=="NetEng"){
			document.getElementById("NetMgmtNav").style.display="inline";
			document.getElementById("CustServNav").style.display="inline";

			}
		if(level=="SupEng"){
			document.getElementById("CustServNav").style.display="inline";

			}
		}





function showDates() {
	document.getElementById("startDateLabel").style.visibility = "visible";
	document.getElementById("modelDate1").style.visibility = "visible";
	document.getElementById("endDateLabel").style.visibility = "visible";
	document.getElementById("modelDate2").style.visibility = "visible";
}

function hideDates() {
	document.getElementById("startDateLabel").style.display = "none";
	document.getElementById("startDateLabel").style.display = "none";
	document.getElementById("modelDate1").style.display = "none";
	document.getElementById("endDateLabel").style.display = "none";
	document.getElementById("modelDate2").style.display = "none";
} 

function changeLabel(value){

	if(value == 1){
		document.getElementById("SearchDiv").style.display = "block";
		document.getElementById("modelSearch").placeholder = "Enter Failure Class...";
		document.getElementById("DateDiv1").style.display = "none";
		document.getElementById("DateDiv2").style.display = "none";
		//hideDates();
	}else if(value == 2){
		document.getElementById("SearchDiv").style.display = "none";
		document.getElementById("DateDiv1").style.display = "block";
		document.getElementById("DateDiv2").style.display = "block";
		//showDates();
	
	}else if(value == 3){
		document.getElementById("SearchDiv").style.display = "block";
		document.getElementById("modelSearch").placeholder = "Enter Phone Model";
		//document.getElementById("searchType").style.visibility = "visible";
		
		document.getElementById("DateDiv1").style.display = "block";
		document.getElementById("DateDiv2").style.display = "block";
		//showDates();
	}	

	return true;
}

function deleteAllRows() {

	var myTable = document.getElementById("tableid");
	var rowCount = myTable.rows.length;
	for (var i = 0; i < rowCount; i++) {
		myTable.deleteRow(0);
	}
}



function lookupImsiEventCallFailure() {

	var JSON_object;
	
	var addUrl = "";
	var suppSelection = document.getElementById("suppSelection").value;
	var VarNumber = document.getElementById("modelSearch").value;
	var startDate = document.getElementById("modelDate1").value;
	var endDate = document.getElementById("modelDate2").value;

	
	if(suppSelection == "1"){
		addUrl = "/1/"+ VarNumber+ "/null"+ "/null";
	}else if(suppSelection == "2"){

		validate = checkDateField(startDate, endDate);
		if(validate == false){
			return false;
		}
						
		addUrl = "/2"+ "/null" + "/" + startDate + "/" + endDate;

	}else if(suppSelection == "3"){

		validate = checkDateField(startDate, endDate);
		if(validate == false){
			return false;
		}
		addUrl = "/3/" + VarNumber + "/" + startDate + "/" + endDate;
	}
    
    getRequest( { 
        	url: "http://localhost:8080/RestJPA/services/message/supportengineer" + addUrl,
            payload: null,
            handler: function(response) { 
                
            	var para1 =document.getElementById("sup1");
            	var para2 =document.getElementById("sup2");
            	var para3 =document.getElementById("sup3");
            	para1.style.color = "black";
		    	para2.style.color = "black";
		    	para3.style.color = "black";
		    	
            	JSON_object = JSON.parse(response);
            	deleteAllRows();

				if(suppSelection == "1"){
					poputateCount(JSON_object.data);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
				}
				else if(suppSelection == "2"){
					poputateImsis(JSON_object.data);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
				}
				else{
					poputateCount3(JSON_object.data);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
				}
				
				
				 //document.getElementById("notify").innerHTML = "Status: Query was Successfull";
                    //JSON.parse(response).result; 
               }
      		} );
 }

function poputateImsis(jsonObj){
	var table = document.getElementById("tableid");
	var row = table.insertRow(0);
	var cell1 = row.insertCell(0);
	//var cell2 = row.insertCell(1); 


	cell1.innerHTML = "IMSI";
	// cell2.innerHTML = "IMSI";


	for(var i = 0; i < jsonObj.length; i++){
		row = table.insertRow(i+1);
		cell1 = row.insertCell(0);
		//cell2 = row.insertCell(1); 

		//cell1.innerHTML = jsonObj[i].failure.failureClass;
		cell1.innerHTML = JSON.stringify(jsonObj[i]);
	}       
}

	function poputateCount(jsonObj){
		var table = document.getElementById("tableid");
		var row = table.insertRow(0);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1); 


		cell1.innerHTML = "Failure Class";
		cell2.innerHTML = "IMSI";


		for(var i = 0; i < jsonObj.length; i++){
			row = table.insertRow(i+1);
			cell1 = row.insertCell(0);
			cell2 = row.insertCell(1); 

			cell1.innerHTML = jsonObj[i].failure.failureClass;
			cell2.innerHTML = jsonObj[i].imsi;

		}       
	}

	function poputateCount3(jsonObj){
		var table = document.getElementById("tableid");
		var row = table.insertRow(0);
		var cell1 = row.insertCell(0);

		cell1.innerHTML = "Failure Count";

		row = table.insertRow(1);
		cell1 = row.insertCell(0);

		cell1.innerHTML = jsonObj.length;

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
	        	var para1 =document.getElementById("sup1");
            	var para2 =document.getElementById("sup2");
            	var para3 =document.getElementById("sup3");
	        	var JSON_object = JSON.parse(http_request.responseText);
	        	para1.style.color = "red";
		    	para2.style.color = "red";
		    	para1.innerHTML = JSON_object.notification;
		    	para2.innerHTML = JSON_object.notification1;
			}
	    };
	    http_request.send( request.payload );
	}

	function checkDateField(startdatetime, enddatetime){

		 if(startdatetime == 0 || enddatetime == 0 || startdatetime > enddatetime){
			 alert('Please Enter Valid DateTime');
		     return false;
		 }
		 return true;
	}
</script>



</head>

	<body id= "backgBody">
			<div id="containter">

				<div id="banner">

					<!--img class="logo" src="elogo.png"-->
					<h1><font face="verdana" color="white" style = "vertical-align: text-bottom;">LTE Call Failure Lookup</font></h1>
					<h2><font face="verdana" color="white" style = "vertical-align: text-bottom;">Support Engineer</font></h2>

				</div>
	<div id="sidebar">
			<div id = "signedIn" style ="padding-left: 10px;">
				<p id="user" style = "font-size:20px;">User:</p>
				<p id="role">Role:</p>
						<br>
						<form  style ="padding-left: 10px;">
						<input type="button" name="Logout" value= "logout" style = "width:160px; height:25px; align:center;" onclick = "logout();">
						</form>
					</div>
					<hr>

						
					<h1 id="filter">Please Choose a Query</h1>
					<hr>


					<div class="options">
						

					 <div class="option1" style =" padding-left: 10px; align:left;">

						</div> 
						
						<div class="option3" style =" padding-left: 10px; align:left;">
							
							<form action="SuppEng" method="get">
								<select id="suppSelection" name="suppSelection" onchange="changeLabel(this.value);">
									<option value="1">Search by Failure Class</option>
									<option value="2">Failed IMSI between Dates</option>
									<option value="3">Count Failures by phone model</option>							
								</select> 
								
								<br>
								<br>
								<div id ="SearchDiv" style="display:block;">
								<input type="text" id="modelSearch" name="modelSearch" placeholder = "Enter Failure Class..." style = "width:210px; display:block;"/></td>
								</div>

								<div id="DateDiv1"  style="display:none;">
									<label class="sidebar" id="startDateLabel" for="modelDate1" style="display:block;"> Start Date:</label>
							 		<input type="datetime-local" id="modelDate1" name="modelDate1" style="display:block;">
							 	</div>

							 	<div id="DateDiv2" style="display:none;">
							 		<label class="sidebar" id="endDateLabel" for="modelDate2" style="display:block;">End Date: </label>
						 			<input type="datetime-local" id="modelDate2" name="modelDate2" style="display:block;">	
						 		</div>
							<br>
							
							<input type="button" name="action" value="Search" style = "width:160px; height:25px; align:center;" onclick="lookupImsiEventCallFailure();" >
						</form>
					</div>

				</div>
			<hr>
			<h1 id="filter" style = "display: block">Move Page</h1>
			<hr>
			<div id="navigate" style= "padding-left: 10px;">
				<table>
				<tr> <td><button  style = "width:160px; height:25px; align:center;"class="nav"  id="CustServNav" value="Customer Service" onclick="location.href='/RestJPA/CustServiceRep.jsp'">Customer Service </button></td> </tr>
				<tr><td> <button  style = "width:160px; height:25px; align:center;"class="nav"  id="NetMgmtNav" value="Network Mgmt" onclick="location.href='/RestJPA/NetMgmtEng.jsp'"> Network Mgmt</button></td> </tr>
				<tr> <td><button  style = "width:160px; height:25px; align:center;"class="nav"  id="sysAdminNav" value="System Admin" onclick="location.href='/RestJPA/sysAdmin.jsp'">System Admin </button></td> </tr>
				</table>
			
			</div>	
					
				</div> 
				
				
				
				<div class="output"></div>
					<div id="summary">
						<p id="sup1"></p>
						<p id="sup2"></p>
						<p id="sup3"></p>
					</div>
				</div>

				<div id="results">
					<div class="CSSTableGenerator">
					<table  id="tableid" cellspacing="0" cellpadding="1" border="1" align="center">
						
					</table>
				</div>	

			</div>
		<script src="cookielib.js" type="text/javascript"></script>
	</body>

</html>
