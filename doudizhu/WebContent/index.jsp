<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="game.doudizhu.*" %>
<%@ page import="game.json.*" %>
	
<%
	
	String key = session.getId();
	Desktop desktop = Game.theGame().getDesktop();
	DesktopInfo di = null;
	
	synchronized (desktop) {
		di = desktop.getDesktopInfo(key);
		//
		//	如果当前用户没有登录,尝试获取参数来登录.
		//	如果参数都没有,则显示登录页
		//
		if (di.getCurrentUser()==null) {
			if (request.getParameter("name") != null) {
				Actor me = desktop.lookerLogin(key, request.getParameter("name"), request.getRemoteAddr());
				if (me != null)
					di = desktop.getDesktopInfo(key);
				else
					request.setAttribute("message", "对不起，因为人数限制，您无法登录");
			}
		}
	}
	
	if (di.getCurrentUser()==null) {
		request.setAttribute("di", di);
		pageContext.forward("anonymous.jsp");
		return;
	}
	
	Actor me = di.getCurrentUser();
	Card[][] cards= di.getCards();

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Web 斗地主</title>
<style type=""></style>
<script src="js/prototype.js"></script>
<script src="js/behaviour.js"></script>
<script src="js/doudizhu.js"></script>
<link href="css/screen.css" text="text/css" rel="stylesheet" />
<script language="javascript">
//initialize: function(isActor,sessionKey,keyHashCode,name,actorType,status,ip,actorIndex) {

var DESKTOPSTATE_READY = 1;
var DESKTOPSTATE_RUNNING = 2;
	
var di = {
"me": <%=me.toJsObject()%>,
"desktopState": <%=di.getDesktopState()%>,
"actors": <%=JsCreator.toJsObject(di.getActors())%>,
"lookers": <%=JsCreator.toJsObject(di.getLookers())%>,
"cards": [<%=JsCreator.toJsObject(cards[0])%>,<%=JsCreator.toJsObject(cards[0])%>,<%=JsCreator.toJsObject(cards[0])%>],
"currentCardPutter": <%=di.getCurrentCardPutter()%>,
"actorCount": <%=di.getActorCount()%>,
"lookerCount": <%=di.getLookerCount()%>

};

</script>

<style>
body {
	text-align:center;
	margin:0px;
	padding:0px;
}

#mainPane {
	position: relative;
	text-align:left;
	width:980px;
}
	
#bodyContainer {
	padding: 20px;
	
	position:relative;
	text-align:left;
}

h1,h2,h3,h4,h5 {
	margin:0px;
	padding:0px;
}

h1 {
	border-top: 1px solid #3366cc;
	background-color:#e5ecf9;
	font-size: 100%;
	padding: 3px;
}
#pane0, #pane1, #pane2, #desktop{
	position: absolute;
	border: 1px solid blue;
	
}

#desktop {
	border-color: red;
}


</style>
</head>
<body>
<div id="bodyContainer">
	<h1>Web 斗地主</h1>
<div id="mainPane">	
	<div id="pane1">
	</div>

	<div id="pane0">
	
	</div>
	<div id="pane2">
		
	</div>	
	<div id="desktop">
		
	</div>
	
</div>
</div>
<script>
	//
	//	1.呈现当前游戏.
	//	2.呈现当前用户可操作的状态
	//
	var Game = Class.create();
	
	Game.prototype = {
		initialize: function() {
			this.cardWidth= 71;
			this.cardHeight= 96;
			this.paneWidth= 100;
		}
	}
	
	var game = new Game();
	
	
	function init_setDiv() {
		var main = $("mainPane");
		var p0 = $("pane0");
		var p1 = $("pane1");
		var p2 = $("pane2");
		var d = $("desktop");
		p0.style.position = p1.style.position = p2.style.position = d.style.position = "absolute";
		
		p0.style.width = p1.style.width = game.paneWidth;
		p0.style.height = p1.style.height = 400;
		p0.style.left = main.offsetWidth-game.paneWidth;
		p0.style.top = 0;
		
		p1.style.top = 0;
		p1.style.left = 0;
		
		p2.style.top = 400;
		p2.style.left = p1.offsetWidth;
		p2.style.width = main.offsetWidth - p0.offsetWidth - p1.offsetWidth;
		p2.style.height = 100;
		
		d.style.top = 0;
		d.style.height = 400;
		d.style.left = p1.offsetWidth;
		d.style.width = p2.style.width;
	}
	
	function btnJoin_onClick(btn) {
		alert (btn.id);
	}
	
	function removeJoinButton() {
		var btn = $("btnJoin");
		if (btn != null) {
			btn.parentElement.removeChild(btn);
		}
	}
	
	function addJoinButton() {
		var btn = document.createElement("input");
		var d = $("desktop");
		var bs = btn.style;
		btn.type = "button";
		btn.value = " 加  入 ";
		d.appendChild(btn);
		btn.id = "btnJoin";
		bs.position = "absolute";
		bs.top = (d.offsetHeight - btn.offsetHeight) - 20;
		bs.left = (d.offsetWidth - btn.offsetWidth) / 2;
		
		btn.onclick = function () {
			btnJoin_onClick(this);
		};
	}
	
	function addActor(obj) {
		
	}
	
	function startBackgroundConnection() {
		
	}
	
	function init() {
		init_setDiv();
		if (di.actorCount < 3) {
			addJoinButton();
		}
	}
	
	
</script>

<script>	
	window.onload = init;
</script>
<iframe height="0" width="0" src="background.jsp"></iframe>
</body>
</html>