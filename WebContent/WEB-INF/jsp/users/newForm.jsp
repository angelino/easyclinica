<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Novo Convênio :.</title>
	</head>
	<body>

		<div class="box" id="usuarios">
			<div class="boxcontent">
			   	<h2>Novo Convênio</h2>
								
				<c:url value="/usuarios" var="formAction" />
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />
				</jsp:include>
			</div>
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:notice notice="Para exibir sua foto nas mensagens trocadas, basta cadastrar seu e-mail e sua foto no site www.gravatar.com." title="Foto no dashboard"></helper:notice>		
		</div>
	</body>
</html>