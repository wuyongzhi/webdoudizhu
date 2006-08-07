<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Web 斗地主</title>
<style>
	body {
		text-align:center;
	}
	
	div.body {
		width: 400px;
		text-align: justify ;
	}
	div.body {
		padding-top:  120px;
	
	}
	
	ul {
		list-style-image: none;
		list-style: none;
	}
	
	em {
		font-style:normal;
		color: #999999;
		font-size: 85%;
	}
	
	#message {
		padding-left:40px;
	}
	
</style>
<script>
	window.onload = "";
</script>
</head>
<body>
<div class="body">
	当前有 <ul>
		<li>${di.actorCount} 位参与者  </li>
		<li>${di.lookerCount } 位旁观者 </li>
		</ul>
	
	<br/><br/>
	
	<form>
		<em id="message">${message}</em>
		<br/>
		
		<label for="name">昵称:</label><input id="name" name="name" type="text" size="20" maxlength="50"  value="${param.name }"/>
		<input type="submit" autocomplete="off" value="进入斗地主" />
	</form>
</div>
</body>
</html>