<%@ page language="java" contentType="text/javascript; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="game.doudizhu.*" %>
<%@ page import="game.doudizhu.web.*" %>
<%
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Expires", "0");
%>
<%
	String key = session.getId();
	Game.theGame().getDesktop();
%>
executeScript("background-script.jsp");