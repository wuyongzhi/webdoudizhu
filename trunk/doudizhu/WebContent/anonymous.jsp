<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Web ������</title>
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
	��ǰ�� <ul>
		<li>${di.actorCount} λ������  </li>
		<li>${di.lookerCount } λ�Թ��� </li>
		</ul>
	
	<br/><br/>
	
	<form>
		<em id="message">${message}</em>
		<br/>
		
		<label for="name">�ǳ�:</label><input id="name" name="name" type="text" size="20" maxlength="50"  value="${param.name }"/>
		<input type="submit" autocomplete="off" value="���붷����" />
	</form>
</div>
</body>
</html>