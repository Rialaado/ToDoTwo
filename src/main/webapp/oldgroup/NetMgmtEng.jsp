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



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

	
	<title>Network Management</title>
	<link href="style.css" rel="stylesheet" type="text/css" media="all" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script type="text/javascript">

	function deleteAllRows() {
		var myTable = document.getElementById("tableid");
		var rowCount = myTable.rows.length;
		for (var i = 0; i < rowCount; i++) {
			myTable.deleteRow(0);
		}
	}
	
			var userLevel=null;
	
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

		
	
	function setButtons(){
	
			if(userLevel=="NetEng"){
				document.getElementById("suppNav").style.display="inline";
				document.getElementById("CustServNav").style.display="inline";
			}
	
			if(userLevel=="SysAdmin"){
				document.getElementById("suppNav").style.display="inline";
				document.getElementById("CustServNav").style.display="inline";
				document.getElementById("sysAdminNav").style.display="inline";
			}

	}	
			
	function logout(){
		deleteCookie("loginservice1");
		deleteCookie("loginservice2");
		deleteCookie("loginservice3");
		window.location.assign("http://localhost:8080/RestJPA/login.html"); 
	}
				
				
	//////////////////////////////To be moved				////////////////////////////////////////
	function lookupHistoryQuery() {
				var JSON_object;
			
				var addUrl = "";
				var accountName = document.getElementById("modelSearch").value;
				var accountType = document.getElementById("netSelect").value;
				//var accountType = "Customer Service Rep";
			
				if(accountName != null && accountType  != null){
					addUrl = accountName+"/"+ accountType;
				}

	
		var addUrl = "";
		var accountName = document.getElementById("modelSearch").value;
		var accountType = document.getElementById("netSelect").value;
	
	
		if(accountName != null && accountType  != null){
			addUrl = accountName+"/"+ accountType;
		}

			getRequest( { 
		    	url: "http://localhost:8080/RestJPA/services/message/history/" + addUrl,
		        payload: null,
		        handler: function(response) { 
		        	var para1 =document.getElementById("sys1");
	            	var para2 =document.getElementById("sys2");
	            	var para3 =document.getElementById("sys3");
	            	para1.style.color = "black";
			    	para2.style.color = "black";
			    	para3.style.color = "black";
			    	
		        	JSON_object = JSON.parse(response);
		        	deleteAllRows();
					poputateHistoryDetails(JSON_object.data);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2; 
					//para3.innerHTML = "Total Number Records: "+"<b>"+JSON_object.data.length +"</b>";
		           }
		  		} );
		
		}

	//////////////////////////////////////////////////////////////////////////////////////
		
	function poputateHistoryDetails(jsonObj){
		var table = document.getElementById("tableid");
		var row = table.insertRow(0);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1); 
		var cell3 = row.insertCell(2); 
		var cell4 = row.insertCell(3);
	
		cell1.innerHTML = "Enquiry ID";
		cell2.innerHTML = "DateTime";
		cell3.innerHTML = "UserName";
		cell4.innerHTML = "Enquiry Type";  
		
	
		for(var i = 0; i < jsonObj.length; i++){
			row = table.insertRow(i+1);
			cell1 = row.insertCell(0);
			cell2 = row.insertCell(1);
			cell3 = row.insertCell(2); 
			cell4 = row.insertCell(3);

			cell1.innerHTML = jsonObj[i].enquiryID;
			var webDate = new Date(jsonObj[i].dateTime);
			cell2.innerHTML = webDate.toUTCString();
			cell3.innerHTML = jsonObj[i].account.username;
			cell4.innerHTML = jsonObj[i].enquiryType;
		}       
	}


	//////////////////////////       Main          //////////////////////////////////////// 
	function lookupImsiEventCallFailure() {
	
		
		
		var JSON_object;

		var addUrl = "";
		var netMgmtSelection = document.getElementById("netMgmtSelection").value;
		var modelSearch = document.getElementById("modelSearch").value;
		var startDate = document.getElementById("modelDate1").value;
		var endDate = document.getElementById("modelDate2").value;

		if(netMgmtSelection == 1){
			addUrl = "/1"+ "/" + "null" + "/" + startDate + "/" + endDate;
		}
		else if(netMgmtSelection == 2){
			addUrl = "/2/"+ modelSearch + "/" + "null" + "/" + "null";
		}
		else if(netMgmtSelection == 3){
			addUrl = "/3"+ "/" + modelSearch + "/" + startDate + "/" + endDate;
		}
		else if(netMgmtSelection == 4){
			addUrl = "/4"+ "/top10ImsiFailure" + "/" + startDate + "/" + endDate;
		}
		else if(netMgmtSelection == 5){
			addUrl = "/5"+ "/" + modelSearch + "/" + startDate + "/" + endDate;
			lookupHistoryQuery();
		}
		else if(netMgmtSelection == 6){
			addUrl = "/6"+ "/" + "null" + "/" + "null" + "/" + "null";
		}



	    getRequest( { 
        	url: "http://localhost:8080/RestJPA/services/message/networkmanagement" + addUrl,
            payload: null,

            handler: function(response) { 
            	var para1 =document.getElementById("sys1");
            	var para2 =document.getElementById("sys2");
            	var para3 =document.getElementById("sys3");
            	para1.style.color = "black";
		    	para2.style.color = "black";
		    	para3.style.color = "black";

            	JSON_object = JSON.parse(response);
            	deleteAllRows();


				if(netMgmtSelection == "1"){
					poputateImsiOccurrenceCountAndDuration(JSON_object);
					createImsiDurationChart(JSON_object);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
					para3.innerHTML = "Total Number Records: "+"<b>"+JSON_object.length +"</b>";
				}else if(netMgmtSelection == "2"){
					createChart(JSON_object);
					poputateCauseCodeCountByModel(JSON_object);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
					//para3.innerHTML = "Total Number Records: "+"<b>"+JSON_object.length +"</b>";
				}else if(netMgmtSelection == "4"){
					poputateTop10ImsiFailure(JSON_object.data);
					createChartTop10ImsiFailure(JSON_object.data, startDate, endDate);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
					//para3.innerHTML = "Total Number Records: "+"<b>"+JSON_object.data.length +"</b>";
				}else if(netMgmtSelection == "6"){
					poputateMarketOperatorCellIDCount(JSON_object);
					createCountChart(JSON_object);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
					//para3.innerHTML = "Total Number Records: "+"<b>"+JSON_object.length +"</b>";
				}else{
					poputateCount3(JSON_object);
					createChart(JSON_object);
					para1.innerHTML = JSON_object.notification1;
					para2.innerHTML = JSON_object.notification2;
				}

               }
      		} );
	    document.getElementById("sliderGraphTableDiv").style.display = "block"
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
	        	var para1 =document.getElementById("sys1");
            	var para2 =document.getElementById("sys2");
            	var para3 =document.getElementById("sys3");
	        	var JSON_object = JSON.parse(http_request.responseText);
	        	para1.style.color = "red";
		    	para2.style.color = "red";
		    	para1.innerHTML = JSON_object.notification;
		    	para2.innerHTML = JSON_object.notification1;
		    }
	    };
	    http_request.send( request.payload );
	}



	function poputateTop10ImsiFailure(jsonObj){

		var table = document.getElementById("tableid");
		var row = table.insertRow(0);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1); 
	
		cell1.innerHTML = "Top 10 IMSI";
		cell2.innerHTML = "No of Failures";
	
		for(var i = 0; i < jsonObj.length; i++){
			row = table.insertRow(i+1);
			cell1 = row.insertCell(0);
			cell2 = row.insertCell(1);
	
			cell1.innerHTML = jsonObj[i].imsi;
			cell2.innerHTML = jsonObj[i].noOfFailure;
		}       
	}

	 

		function poputateMarketOperatorCellIDCount(jsonObj){
			var table = document.getElementById("tableid");
			var row = table.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1); 
			var cell3 = row.insertCell(2); 
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);

			totalFails = jsonObj.allEventsCount;
			
			
			cell1.innerHTML = "CellID"; 
			cell2.innerHTML = "Market";
			cell3.innerHTML = "Operator";  
			cell4.innerHTML = "Count";

			cell5.innerHTML = "% Total failures";

			for(var i = 0; i < jsonObj.events.length; i++){
				row = table.insertRow(i+1);
				cell1 = row.insertCell(0);
				cell2 = row.insertCell(1);
				cell3 = row.insertCell(2); 
				cell4 = row.insertCell(3);
				cell5 = row.insertCell(4);

				percentFail = (jsonObj.occurances[i]/totalFails)*100;
			
				cell1.innerHTML = jsonObj.events[i].cellHier.cellID;
				cell2.innerHTML = jsonObj.events[i].operatorBean.id.mnc;
				cell3.innerHTML = jsonObj.events[i].operatorBean.id.mcc;
				cell4.innerHTML = jsonObj.occurances[i];
				cell5.innerHTML = percentFail.toFixed(2) + "%";
				} 
			}
		
		function poputateImsiOccurrenceCountAndDuration(jsonObj){

			var table = document.getElementById("tableid");
			var row = table.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1); 
			var cell3 = row.insertCell(2); 

			cell1.innerHTML = "IMSI";
			cell2.innerHTML = "No of Occurrences";
			cell3.innerHTML = "Total Duration";  
			
		
			for(var i = 0; i < jsonObj.occurances.length; i++){
				row = table.insertRow(i+1);
				cell1 = row.insertCell(0);
				cell2 = row.insertCell(1);
				cell3 = row.insertCell(2); 
			
				cell1.innerHTML = jsonObj.distinctImsi[i];
				cell2.innerHTML = jsonObj.occurances[i];
				cell3.innerHTML = jsonObj.durations[i];
			}
		}




		
		function poputateCauseCodeCountByModel(jsonObj){
			var table = document.getElementById("tableid");
			var row = table.insertRow(0);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1); 
			var cell3 = row.insertCell(2); 
			var cell4 = row.insertCell(3);
		
			cell1.innerHTML = "Model";
			cell2.innerHTML = "Cause Code";
			cell3.innerHTML = "Event ID";
			cell4.innerHTML = "Occurances";  
			
			for(var i = 0; i < jsonObj.events.length; i++){
				row = table.insertRow(i+1);
				cell1 = row.insertCell(0);
				cell2 = row.insertCell(1);
				cell3 = row.insertCell(2); 
				cell4 = row.insertCell(3);
			
				cell1.innerHTML = jsonObj.events[i].ue.model;
				cell2.innerHTML = jsonObj.events[i].eventdetail.id.causeCode;
				cell3.innerHTML = jsonObj.events[i].eventdetail.id.eventID;
				cell4.innerHTML = jsonObj.occurances[i];
			}       
		}
		
		
		function showTableData()
		{
			document.getElementById("results").style.display = 'block';
		    document.getElementById("chartForData").style.display = 'none';
		    document.getElementById("results").style.overflow = "scroll";
		    document.getElementById("chartForData").style.overflow = "hidden";
		}
		
		function showGraphData()
		{
			document.getElementById("results").style.display = 'none';
		    document.getElementById("chartForData").style.display = 'block';
		    document.getElementById("results").style.overflow = "hidden";
		    document.getElementById("chartForData").style.overflow = "scroll";
		
		}

		//Added show/ hide dates
		function showDates() {
			document.getElementById("startDateLabel").style.display = "block";
			document.getElementById("modelDate1").style.display = "block";
			document.getElementById("endDateLabel").style.display = "block";
			document.getElementById("modelDate2").style.display = "block";
		}

		function hideDates() {
			document.getElementById("startDateLabel").style.display = "none";
			document.getElementById("modelDate1").style.display = "none";
			document.getElementById("endDateLabel").style.display = "none";
			document.getElementById("modelDate2").style.display = "none";
		} 


		///Added a change label funct
		function changeLabel(value){
			if(value == 1){

				document.getElementById("modelSearch").style.display = "none";
				showDates();
			}
			if(value == 2){
				document.getElementById("modelSearch").style.display = "block";
				document.getElementById("modelSearch").placeholder = "Enter a Model..."
				document.getElementById("netSelect").style.display = "none";
				hideDates();
			
			}
			if(value == 3){   //Top 10 Market, Operator and Cell combinations
				document.getElementById("modelSearch").style.display = "none";
				document.getElementById("netSelect").style.display = "none";
				showDates();
			}
			if(value == 4){   //Top 10 IMSIs during a period
				document.getElementById("modelSearch").style.display = "none";
				document.getElementById("netSelect").style.display = "none";
				showDates();
			}
			
			if(value == 5){ //User history
				document.getElementById("modelSearch").style.display = "block";
				document.getElementById("modelSearch").placeholder = "Enter a Username...";
				document.getElementById("netSelect").style.display = "block";
				hideDates();
			}
			if(value == 6){ //Analysis Market, Operator and Cell (Graph)
				document.getElementById("modelSearch").style.display = "none";
				document.getElementById("netSelect").style.display = "none";
				hideDates();
			}
		}

		
		
		function graphTableSwitch()
		{
			if(document.getElementById("myonoffswitch").checked==false)
			{
				showGraphData();
			}
			else
			{
				showTableData();
			}
		}

	
	</script>
		
</head>
   
<body id= "backgBody">
	<div id="containter">
		<div id="banner">
			<!--img class="logo" src="elogo.png"-->   
			<h1><font face="verdana" color="white" style = "vertical-align: text-bottom;">LTE Call Failure Lookup</font></h1>
			<h2><font face="verdana" color="white" style = "vertical-align: text-bottom;">Network Management Engineering</font></h2>
	</div>    		
		
		<div id="sidebar">
			<div id = "signedIn" style ="padding-left: 10px;">
				<p id="user" style = "font-size:20px;">User:</p>
				<p id="role">Role:</p>
				
				<br>
				<form style ="padding-left: 5px;">
					<input type="button" name="Logout" value= "Logout" style = "width:160px; height:25px; align:center;" onclick = "logout();">
				</form>
				
			</div>
			<hr>
			
			<h1 id="filter">Please Choose a Query</h1>
				<hr>
					<br>
					<div style= "padding-left: 10px;">
					<form action="NetMgmtEng" method="get">
						<select id="netMgmtSelection" name="netMgmtSelection" onchange="changeLabel(this.value);" style = "width:220px; height:25px; align:center;">
							<option value="1">Number call failures and duration</option>
							<option value="2">Models unique failure event and cause</option>
							<!-- <option value="3">Top 10 Market, Operator and Cell</option> -->
							<option value="4">Top 10 IMSIs during a period</option>
							<option value="5">User history</option>
						  	<option value="6">Analysis: Market, Operator, Cell</option>  
						</select>
			
					</form>
					
					
					<table>
						<tr>
							<br>
							<input type="text" id="modelSearch" name="modelSearch" placeholder = "Enter IMSI..."; style = "width:210px; display:none;"/></td>
							
						</tr>
						<tr>
							<td><label class="sidebar" id="startDateLabel" for="modelDate1"  style = "display:block;"> Start Date:</label></td>
							<td><input type="datetime-local" id="modelDate1" name="modelDate1" style="display:block; width:175px; height:25px; align:center;"></td>
						</tr>
						<tr>
							<td><label class="sidebar" id="endDateLabel" for="modelDate2" style="display:block;">End Date: </label></td>
							<td><input type="datetime-local" id="modelDate2" name="modelDate2" style="display:block; width:175px; height:25px; align:center;"></td>
						</tr>
						
					</table>
					<select style ="display:none;" name = "netSelect" id = "netSelect">
						  <option value="CustRep" >Customer Service  Rep </option>
						  <option value="SupEng">Support Engineer</option>
					</select>
					<br>	
					<input style = "width:160px; height:25px; align:center;" type="button" name="action" value="Search Query" onclick="lookupImsiEventCallFailure();" >
						
						
						</div>
						<br>
						<div id ="sliderGraphTableDiv" style = "display:none"><br>
						<p style = "float: left; padding-left: 15px; padding-right: 30px;" >Graph</p>
						<p style = "float: right;padding-right: 65px;">Table</p>
						<div class="onoffswitch"  style = "width:130px; margin-right: 10px" >
						<input type="checkbox" name="onoffswitch"
							class="onoffswitch-checkbox" id="myonoffswitch" onchange="graphTableSwitch();" checked> <label
							class="onoffswitch-label" for="myonoffswitch">
							<div class="onoffswitch-inner"></div>
							<div class="onoffswitch-switch"></div>
						</label>
					</div>
					
					</div>
									
					<hr>
					<h1 id="filter">Move Page</h1>
					<hr>
						<div id="navigate" style= "padding-left: 10px;">
							<table>
								<tr><td><button  style = "width:160px; height:25px; align:center;" class="nav" id="suppNav" value="Support" onclick="location.href='/RestJPA/supportEngineer.jsp'">Support </button></td> </tr>
								<tr><td><button  style = "width:160px; height:25px; align:center;" class="nav" id="CustServNav" value="Customer Service" onclick="location.href='/RestJPA/CustServiceRep.jsp'">Customer Service </button></td> </tr>
								<tr><td><button  style = "width:160px; height:25px; align:center;" class="nav" id="sysAdminNav" value="System Admin" onclick="location.href='/RestJPA/sysAdmin.jsp'">System Admin  </button></td> </tr>
							</table>
						</div>			



				</div>

				<div id="summary">
					<p id="sys1"></p>
					<p id="sys2"></p>
					<p id="sys3"></p>
				</div>
				
				<div id="results">
					<table  class="CSSTableGenerator"  id="tableid" cellspacing="0" cellpadding="1" border="1" align="center">
					</table>
				</div>						
			</div>
			<div id="chartForData" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

			<script src="cookielib.js" type="text/javascript"></script>

			
			<div id="chartForData" style="min-width: 310px; height: 400px; margin: 0 auto"></div>		
			<script src="createChart.js" type="text/javascript"></script>	
			<script src="createCountChart.js" type="text/javascript"></script>	
			<script src="chartTop10ImsiFailure.js" type="text/javascript"></script>
			<script src="cookielib.js" type="text/javascript"></script>
			<script src="createImsiDurationChart.js" type="text/javascript"></script>
			
	</body>
</html>
