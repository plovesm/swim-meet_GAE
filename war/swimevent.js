function loadResponse(methodIn, urlIn, parmsIn, callbackIn)
{
	var xmlhttp;
	var method = (methodIn == null) ? "GET" : methodIn;
	var url = (urlIn == null) ? "http://localhost:8888/swim_meet" : urlIn;
	var parms = (parmsIn == null) ? "" : parmsIn;
	var callback = (callbackIn == null) ? function(){} : callbackIn;
	
	if (window.XMLHttpRequest)
	{
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=callback;
	
	xmlhttp.open(method,url+parms,true);

	xmlhttp.send();
}

function incrementEvent ()
{
	var callback = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var response = xmlhttp.responseText;
			alert(response);
			
			var swimeventObj = JSON.parse(response);
			
			alert(swimeventObj.eventNum);
		}
	};
	
	loadResponse("POST", null, null, callback);
}


function decrementEvent ()
{
	var callback = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var response = xmlhttp.responseText;
			alert(response);
			
			var swimeventObj = JSON.parse(response);
			
			alert(swimeventObj.eventNum);
		}
	};
	
	loadResponse("POST", null, null, callback);
}


function incrementHeat ()
{
	var callback = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var response = xmlhttp.responseText;
			alert(response);
			
			var swimeventObj = JSON.parse(response);
			
			alert(swimeventObj.eventNum);
		}
	};
	
	loadResponse("POST", null, "?eventId="+""+"action=eventInc", callback);
}


function decrementHeat ()
{
	var callback = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var response = xmlhttp.responseText;
			alert(response);
			
			var swimeventObj = JSON.parse(response);
			
			alert(swimeventObj.eventNum);
		}
	};
	
	loadResponse("POST", null, null, callback);
}


function initEvent ()
{
	var callback = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var response = xmlhttp.responseText;
			alert(response);
			
			var swimeventObj = JSON.parse(response);
			
			alert(swimeventObj.eventNum);
		}
	};
	
	loadResponse("GET", null, null, callback);
}

