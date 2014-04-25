function setCookie(name , value , expiry){
	 
	 var expiryDate = "";
	 
	 if(expiry){ 
		 var date = new Date();
		 date.setTime(date.getTime() + expiry * 60000);		 
		 expiryDate = "; expires="+ date.toUTCString();
		 //alert(expiryDate);
	 }
	 
	 document.cookie = name + "=" + escape(value) + expiryDate;
 }

 function getCookie(cname){
	 
	 var name = cname + "=";
	 var ca = document.cookie.split(';');
	 for(var i=0; i<ca.length; i++){
	   var c = ca[i].trim();
	   if (c.indexOf(name)==0) return unescape(c.substring(name.length,c.length));
	 }
	 return "";

 }
 
 function deleteCookie(name){
	 setCookie(name , "" , -1);
 }