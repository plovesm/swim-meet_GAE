function loadResponse(methodIn, urlIn, parmsIn, callbackIn)
{
	var xmlhttp;
	var method = (methodIn == null) ? "GET" : methodIn;
	var url = (urlIn == null) ? "/swim_meet" : urlIn;
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

function updateAction (action)
{
	var eventId = "list";
	var callback = function(responseText)
	{
		var response = responseText;
		var swimeventObj = JSON.parse(response);
		
		if(swimeventObj[0])
		{
			setNumbers(swimeventObj[0].eventNum, swimeventObj[0].heatNum);
		}
		else
		{
			setNumbers(swimeventObj.eventNum, swimeventObj.heatNum);
		}
	};
	
	if(document.getElementById("eventId"))
	{
		eventId = document.getElementById("eventId").value;
	}
	alert("EventId: " + eventId + " , Action: " + action)
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

function refreshEvent()
{
	var eventId = "list";
	var callback = function(responseText)
	{
		var response = responseText;
		var swimeventObj = JSON.parse(response);
		
		if(swimeventObj[0])
		{
			setNumbers(swimeventObj[0].eventNum, swimeventObj[0].heatNum);
		}
		else
		{
			setNumbers(swimeventObj.eventNum, swimeventObj.heatNum);
		}
	};
	
	if(document.getElementById("eventId"))
	{
		eventId = document.getElementById("eventId").value;
	}
	
	loadResponse("GET", null, "?eventId="+eventId, callback);
}

function initEvent()
{
	var callback = function(responseText)
	{
		var response = responseText;
		var swimeventObj = JSON.parse(response);
		
		
		if(swimeventObj[0])
		{
			setEventId(swimeventObj[0].eventId);
			setNumbers(swimeventObj[0].eventNum, swimeventObj[0].heatNum);
		}
		else
		{
			setEventId(swimeventObj.eventId);
			setNumbers(swimeventObj.eventNum, swimeventObj.heatNum);
		}
	};
	
	loadResponse("GET", null, "?eventId=list", callback);
}

function setEventId(eventId)
{
	document.getElementById("eventId").value = eventId;
}

function setNumbers(eventNum, heatNum)
{
	document.getElementById("eventNum").innerHTML = eventNum;
	document.getElementById("heatNum").innerHTML = heatNum;
}