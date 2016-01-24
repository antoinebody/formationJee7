/**
 * 
 */

if(!String.prototype.startsWith){
    String.prototype.startsWith = function (str) {
        return !this.indexOf(str);
    }
}

if(!window.WebSocket)
	throw new Error("WebSocket non supported")
else
	console.log("ws protocol supported")
var webSocket = new WebSocket("ws://localhost:8080/ProjWebsocket/actions/myParameter");
var webSocket2 = new WebSocket("ws://localhost:8080/ProjWebsocket/actions2/myParameter");

webSocket.onmessage = receivedMessage;
webSocket2.onmessage = receivedMessage2;


function closeWS(){
	webSocket.close();
	webSocket2.close();
}

function sendMessage() {
	//Ex 1 : simple String
	 webSocket.send("hello from client");

}

function sendMessage2() {
	//Ex 2 : JSON Object
	var objContact ={
			type : "person",
			firstName : "antoine",
			lastName : "body"
	}
	webSocket2.send(JSON.stringify(objContact));
	 //webSocket.send("hello from client");
	
}

function receivedMessage2(event) {
	var p = JSON.parse(event.data);
	document.getElementById("res2").innerHTML = p.firstName;
	//document.getElementById("res2").innerHTML = event.data;
	
}

function receivedMessage(event) {
	// Ex 1 : received simple String
	//event.data : received data
	
	if(event.data.startsWith("yo")){
		document.getElementById("yop").innerHTML = event.data;
	}
	else{
		document.getElementById("res").innerHTML = event.data;
	}
		
	//Ex 
}