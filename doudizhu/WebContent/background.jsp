<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%><%
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Expires", "0");
%>
<html>
<head>
<script>
function executeScript(src) {
	var script = document.createElement("script");
	document.body.appendChild(script);
	script.src = src;
}

function doSomething() {
	alert ("good");
	executeScript("background-script.jsp");
}
window.onload = doSomething;

</script>
</head>
<body>
</body>
</html>