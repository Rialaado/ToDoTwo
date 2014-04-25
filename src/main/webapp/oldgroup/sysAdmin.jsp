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
	<title>System Admin</title>

	<link href="style.css" rel="stylesheet" type="text/css" media="all" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script type="text/javascript">

	function confirmUserCookie() {
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

		var userLevel;
		
	function setButtons(level){
		if(level=="SysAdmin"){
			document.getElementById("suppNav").style.display="inline";
			document.getElementById("CustServNav").style.display="inline";
			document.getElementById("NetMgmtNav").style.display="inline";		
		}
	}

	var checkingUpload;
	var checkingUploadFinished;

	
	function logout(){
		deleteCookie("loginservice1");
		deleteCookie("loginservice2");
		deleteCookie("loginservice3");
		window.location.assign("http://localhost:8080/RestJPA/login.html"); 
	}



	function uploadingNotification()
	{
		setInterval(function(){
			var prog =document.getElementById("net1");
			if(checkingUploadFinished == true){
				if(checkingUpload == true){
				 	 prog.style.visibility = "visible";
				 	 prog.style.color = "#339966";
			         prog.innerHTML = "<b>"+"Uploading Excel File ............"+ "</b>";
		             checkingUpload = false;
				}else{
	                  prog.style.visibility = "hidden";
	                  checkingUpload = true;
	         }
			}else{
				prog.style.visibility = "visible";
			}
		},1000);
	}

	function loadupload(){
		var para1 =document.getElementById("net1");
    	var para2 =document.getElementById("net2");
    	var para3 =document.getElementById("net3");

    
		var form = document.forms.namedItem("fileinfo");
		form.addEventListener('submit', function(event) {

			para1.innerHTML = "";
	    	para2.innerHTML = "";
	    	para3.innerHTML = "";
	    	
		  var oData = new FormData(document.forms.namedItem("fileinfo"));

		  var httpRequest = new XMLHttpRequest();
		  httpRequest.open("POST", "services/message/uploadfile", true);
		  checkingUpload = true;
		  checkingUploadFinished = true;
		  uploadingNotification();
		  httpRequest.onload = function(oEvent) {
			  
		    if (httpRequest.status == 200) {
		    	var JSON_object = JSON.parse(httpRequest.responseText);
		    	document.getElementById("fileinfo").reset();
		    	para1.innerHTML = JSON_object.notification;
		    	para2.innerHTML = "Total Upload Time is: " + "<b>"+JSON_object.notification1 + "</b>";
		    	para3.innerHTML = "Successfull Record Uploaded: " + "<b>"+JSON_object.notification2 + "</b>"+
		    	" and Failed Records Uploaded: " + "<b>"+JSON_object.notification3 + "</b>";
		    	para1.style.visibility = "visible";	
		    	para1.style.color = "black";
		    	para2.style.color = "black";
		    	para3.style.color = "black";
				
		    	 checkingUpload = false;
		    	 checkingUploadFinished = false;
		    	 
		    } else if(httpRequest.status == 401 || httpRequest.status == 500){
		    	document.getElementById("fileinfo").reset();
		    	var JSON_object = JSON.parse(httpRequest.responseText);
		    	para1.style.visibility = "visible";
		    	para1.style.color = "red";
		    	para2.style.color = "red";
		    	para1.innerHTML = JSON_object.notification;
		    	para2.innerHTML = JSON_object.notification1;
		    	para3.innerHTML = "";
		    	checkingUploadFinished = false;
		    	checkingUpload = false;
			}
		  };

		  httpRequest.send(oData);
		  event.preventDefault();
		}, false);

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
	        	var para1 =document.getElementById("net1");
	        	var para2 =document.getElementById("net2");
	        	var para3 =document.getElementById("net3");
	        	para1.style.color = "red";
		    	para2.style.color = "red";
		    	para1.innerHTML = JSON_object.notification;
		    	para2.innerHTML = JSON_object.notification1;
		    	para3.innerHTML = "";
		     }
	    };
	    http_request.send( request.payload );
	}


	function retrieveAllEvents() {

	var JSON_object;		
	    
	 getRequest( { 
	        	url: "http://localhost:8080/RestJPA/services/message/sysAdminGetEvents",
	            payload: null,
	            handler: function(response) { 
	            	var para1 =document.getElementById("net1");
	            	var para2 =document.getElementById("net2");
	            	var para3 =document.getElementById("net3");
	            	para1.style.color = "black";
			    	para2.style.color = "black";
			    	para3.style.color = "black";
			    	
	            	JSON_object = JSON.parse(response);
	            	deleteAllRows();

	            	populateValidEntryTable(JSON_object);
	            	populateErrorTable(JSON_object);
	            	createChart(JSON_object);
	            	para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2; 
					//document.getElementById("notify").innerHTML = "Status: Query was Successfull";
	               }
	      	} );
	 }

	function populateValidEntryTable(jsonObj){
		var table = document.getElementById("tableid");
		var row = table.insertRow(0);
		var cell2 = row.insertCell(0); 
		var cell3 = row.insertCell(1); 
		var cell4 = row.insertCell(2);
		var cell5 = row.insertCell(3);
		var cell6 = row.insertCell(4);
		var cell7 = row.insertCell(5);
		var cell8 = row.insertCell(6);
		var cell9 = row.insertCell(7);
		var cell10 = row.insertCell(8);
		var cell11 = row.insertCell(9);

		cell2.innerHTML = "Date";
		cell3.innerHTML = "Duration";
		cell4.innerHTML = "Imsi";  
		cell5.innerHTML = "Cell ID";  
		cell6.innerHTML = "Cause Code";  
		cell7.innerHTML = "Event ID";
		cell8.innerHTML = "Failure Class";  
		cell9.innerHTML = "Operator";  
		cell10.innerHTML = "Market"; 
		cell11.innerHTML = "TAC";       
		

		for(var i = 0; i < jsonObj.events.length; i++){
			row = table.insertRow(i+1);
			cell2 = row.insertCell(0);
			cell3 = row.insertCell(1); 
			cell4 = row.insertCell(2);
			cell5 = row.insertCell(3);
			cell6 = row.insertCell(4);
			cell7 = row.insertCell(5); 
			cell8 = row.insertCell(6);
			cell9 = row.insertCell(7);
			cell10 = row.insertCell(8);
			cell11 = row.insertCell(9); 

			var webDate = new Date(jsonObj.events[i].dateTime);
			var dateString = webDate.toString();
			var finalDate = dateString.substring(0,24);
			cell2.innerHTML = finalDate;
			cell3.innerHTML = jsonObj.events[i].duration;
			cell4.innerHTML = jsonObj.events[i].imsi;
			cell5.innerHTML = jsonObj.events[i].cellHier.cellID;
			cell6.innerHTML = jsonObj.events[i].eventdetail.id.causeCode;
			cell7.innerHTML = jsonObj.events[i].eventdetail.id.eventID;
			cell8.innerHTML = jsonObj.events[i].failure.failureClass;
			
			cell9.innerHTML = jsonObj.events[i].operatorBean.id.mnc;
			cell10.innerHTML = jsonObj.events[i].operatorBean.id.mcc;
			cell11.innerHTML = jsonObj.events[i].ue.tac;
			

			showTablesButtons();
		}       
	}

	function populateErrorTable(jsonObj){
		var table = document.getElementById("tableid2");
		var row = table.insertRow(0);
		var cell2 = row.insertCell(0); 
		var cell3 = row.insertCell(1); 
		var cell4 = row.insertCell(2);
		var cell5 = row.insertCell(3);
		var cell6 = row.insertCell(4);
		var cell7 = row.insertCell(5);
		var cell8 = row.insertCell(6);
		var cell9 = row.insertCell(7);
		var cell10 = row.insertCell(8);
		var cell11 = row.insertCell(9);

		cell2.innerHTML = "Date";
		cell3.innerHTML = "Duration";
		cell4.innerHTML = "Imsi";  
		cell5.innerHTML = "Cell ID";  
		cell6.innerHTML = "Cause Code";  
		cell7.innerHTML = "Event ID";
		cell8.innerHTML = "Failure Class";  
		cell9.innerHTML = "Operator";  
		cell10.innerHTML = "Market"; 
		cell11.innerHTML = "TAC";       
		

		for(var i = 0; i < jsonObj.errorEvents.length; i++){
			row = table.insertRow(i+1);
			cell2 = row.insertCell(0);
			cell3 = row.insertCell(1); 
			cell4 = row.insertCell(2);
			cell5 = row.insertCell(3);
			cell6 = row.insertCell(4);
			cell7 = row.insertCell(5); 
			cell8 = row.insertCell(6);
			cell9 = row.insertCell(7);
			cell10 = row.insertCell(8);
			cell11 = row.insertCell(9); 

			var webDate = new Date(jsonObj.errorEvents[i].date_Time);
			var dateString = webDate.toString();
			var finalDate = dateString.substring(0,24);
			cell2.innerHTML = finalDate;
			cell3.innerHTML = jsonObj.errorEvents[i].duration;
			cell4.innerHTML = jsonObj.errorEvents[i].imsi;
			cell5.innerHTML = jsonObj.errorEvents[i].cellId;
			cell6.innerHTML = jsonObj.errorEvents[i].causeCode;
			cell7.innerHTML = jsonObj.errorEvents[i].eventId;
			cell8.innerHTML = jsonObj.errorEvents[i].failure;
			
			cell9.innerHTML = jsonObj.errorEvents[i].operator;
			cell10.innerHTML = jsonObj.errorEvents[i].market;
			cell11.innerHTML = jsonObj.errorEvents[i].ueType;

			showTablesButtons();
		}       
	}

	function deleteAllRows() {

		var myTable = document.getElementById("tableid");
		var rowCount = myTable.rows.length;

		for (var i = 0; i < rowCount; i++) {
			myTable.deleteRow(0);
		}
	}

	function showTablesButtons(){

		document.getElementById("showErrorTableButton").style.display = "block";
		document.getElementById("showDataTableButton").style.display = "block";
		document.getElementById("importDataButton").style.display = "none";
		document.getElementById("ViewGraph").style.display = "block";	
	}

	function showDataTable()
	{
		document.getElementById("results").style.display = 'block';
	    document.getElementById("results").style.overflow = "scroll";
		document.getElementById("errorResults").style.display = 'none';
	    document.getElementById("errorResults").style.overflow = "hidden";
	    document.getElementById("chartForData").style.display = 'none';
	    document.getElementById("chartForData").style.overflow = "hidden";
	}

	function showErrorTable()
	{
		document.getElementById("errorResults").style.display = 'block';
	    document.getElementById("errorResults").style.overflow = "scroll";
	    document.getElementById("results").style.display = 'none';
	    document.getElementById("results").style.overflow = "hidden";
	    document.getElementById("chartForData").style.display = 'none';
	    document.getElementById("chartForData").style.overflow = "hidden";

	}

	function showEventChart()
	{
		document.getElementById("chartForData").style.display = 'block';
	    document.getElementById("chartForData").style.overflow = "scroll";
		document.getElementById("errorResults").style.display = 'none';
	    document.getElementById("errorResults").style.overflow = "hidden";
		document.getElementById("results").style.display = 'none';
	    document.getElementById("results").style.overflow = "hidden";
	}

	

	var createChart = function (jsonObject) {

		$('#chartForData').highcharts({
			 chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		            text: 'Percentage of Valid rows against Error rows'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: 'Total Percentage',
		            data: [
		                ['Error Rows',   jsonObject.errorEvents.length],
		                ['Valid Rows',       jsonObject.events.length]
		               
			            ]
	            }]
	        });
	    };

	</script>

	</head>

	<body id= "backgBody" onload ="loadupload();confirmUserCookie();">
			<div id="containter">

				<div id="banner">

					<!--img class="logo" src="elogo.png"-->
					<h1><font face="verdana" color="white" style = "vertical-align: text-bottom;">LTE Call Failure Lookup</font></h1>
					<h2><font face="verdana" color="white" style = "vertical-align: text-bottom;">System Administration</font></h2>
				</div>

				<div id="sidebar">

			<div id = "signedIn" style ="padding-left: 10px;">
				<p id="user" style = "font-size:20px;">User:</p>
				
				<p id="role">Role:</p>
						<br>
						<form style ="padding-left: 5px;"  >
						<input type="button" name="Logout" value= "Logout" style = "width:160px; height:25px; align:center;" onclick = "logout();">
						</form>
					</div>
					<hr>
					<h1 id="filter">Option 1 - Import Excel </h1>
					<hr>

				
				<form enctype="multipart/form-data" method="post" name="fileinfo"  style ="padding-left: 10px;" id="fileinfo" >
					 <input type="file" name="file" required style="height: 30px; width: 200px;"/> <br>
					 <input type="submit" value="Upload File"  style="height: 25px; width: 160px;"/>
				</form>
				<br>
				<hr>
				<br>
					<form action="" method="get" style =" padding-left: 10px;">
							<input id = "importDataButton" type="button" name="View Import" value= "View Import" style = "width:160px; height:25px; align:center; display:inline;"
						onclick = "retrieveAllEvents();" />
					</form>
					<div style= "padding-left: 10px;">
					<input id = "showDataTableButton" type="button" name="View Data Table" value= "View Data Table" style = "width:160px; height:25px; align:center; display:none; margin-top:7px;" onclick = "showDataTable();"/>
					<input id = "showErrorTableButton" type="button" name="View Error Table" value= "View Error Table" style = "width:160px; height:25px; align:center; display:none; margin-top:7px;" onclick = "showErrorTable();"/>
					<input id = "ViewGraph" type="button" name="View Graph" value= "View Graph" style = "width:160px; height:25px; align:center; display:none; margin-top:7px;" onclick = "showEventChart();"/>
					</div>
					<br>
					<hr>
					<h1 id="filter">Option 2 - Register User</h1>
					<hr>
					<form  style ="padding-left: 10px;">
						<input type="button" name="Register" value= "Register" style = "width:160px; height:25px; align:center; " 
						onclick="window.location.href='./register.html'">
					</form>
					<hr>
					<h1 id="filter">Option 3 - Move Page</h1>
					<hr>
				<div id="navigate" style= "padding-left: 10px;">
					<table>
					<tr><td> <button  style = "width:160px; height:25px; align:center;" class="nav" id="suppNav" value="Support" onclick="location.href='/RestJPA/supportEngineer.jsp'">Support </button></td> </tr>
					<tr> <td><button  style = "width:160px; height:25px; align:center;" class="nav" id="CustServNav" value="Customer Service" onclick="location.href='/RestJPA/CustServiceRep.jsp'">Customer Service </button></td> </tr>
					<tr><td> <button  style = "width:160px; height:25px; align:center;" class="nav" id="NetMgmtNav"  value="Network Mgmt" onclick="location.href='/RestJPA/NetMgmtEng.jsp'"> Network Mgmt</button></td> </tr>
					</table>
				
				</div>	


				</div>

				<div id="summary">

					<p id="net1"></p>
					<p id="net2"></p>
					<p id="net3"></p>

				</div>


				<div id="results" class="CSSTableGenerator">
					<table id="tableid" cellspacing="0" cellpadding="1" border="1" align="center">

					</table>


				</div>
					<div id = "errorResults" class="CSSTableGenerator">
					<table id="tableid2" cellspacing="0" cellpadding="1" border="1" align="center">
					
					</table>
					</div>
			</div>
			<div id="chartForData" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<script src="cookielib.js" type="text/javascript"></script>
	</body>
</html>
