<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script>
	function executeScript() {
		var script = document.createElement("script");
		document.body.appendChild(script);
		script.src = "js/test.js";
	}
</script>
</head>
<body>
<button onclick="executeScript();"></button>
</body>
</html>