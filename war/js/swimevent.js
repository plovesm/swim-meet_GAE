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
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			callback(xmlhttp.responseText);
		}
	};
	
	xmlhttp.open(method,url+parms,true);

	xmlhttp.send();
}

function updateAction (eventId, action)
{
	var callback = function(responseText)
	{
		var response = responseText;
		var swimeventObj = JSON.parse(response);
		
		setNumbers(swimeventObj.eventNum, swimeventObj.heatNum);
		
	};
	
	loadResponse("GET", null, "?eventId="+eventId+"&action="+action, callback);
}


function decrementEvent ()
{
}


function incrementHeat ()
{
}


function decrementHeat ()
{
}


function initEvent (eventId)
{
	var callback = function(responseText)
	{
		var response = responseText;
		var swimeventObj = JSON.parse(response);
		
		setNumbers(swimeventObj.eventNum, swimeventObj.heatNum);
		
	};
	
	loadResponse("GET", null, "?eventId="+eventId, callback);
}

function setNumbers(eventNum, heatNum)
{
	document.getElementById("eventNum").innerHTML = eventNum;
	document.getElementById("heatNum").innerHTML = heatNum;
}