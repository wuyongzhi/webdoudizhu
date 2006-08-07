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
		//	�����ǰ�û�û�е�¼,���Ի�ȡ��������¼.
		//	���������û��,����ʾ��¼ҳ
		//
		if (di.getCurrentUser()==null) {
			if (request.getParameter("name") != null) {
				Actor me = desktop.lookerLogin(key, request.getParameter("name"), request.getRemoteAddr());
				if (me != null)
					di = desktop.getDesktopInfo(key);
				else
					request.setAttribute("message", "�Բ�����Ϊ�������ƣ����޷���¼");
	
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
<title>Web ������</title>
<script src="js/prototype.js"></script>
<script src="js/behaviour.js"></script>
<script src="js/doudizhu.js"></script>

<script language="javascript">
//initialize: function(isActor,sessionKey,keyHashCode,name,actorType,location,status,ip,actorIndex) {
	
var di = {
"me": <%=me.toJsObject()%>,
"desktopState": <%=di.getDesktopState()%>,
"actors": <%=JsCreator.toJsObject(di.getActors())%>,
"lookers": <%=JsCreator.toJsObject(di.getLookers())%>,
"cards": {<%=JsCreator.toJsObject(cards[0])%>,<%=JsCreator.toJsObject(cards[0])%>,<%=JsCreator.toJsObject(cards[0])%>}
"currentCardPutter", <%=di.getCurrentCardPutter()%>,
"actorCount", <%=di.getActorCount()%>,
"lookerCount", <%=di.getLookerCount()%>
};
</script>
</head>
<body>

</body>
</html>