<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.HealthCarePlan"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Editar Convênio :.</title>
	</head>
	<body>

		<div class="box" id="convenios">
			<div class="boxcontent">
		   		<h2>Editar Convênio</h2>
				
				<c:url value="/convenios/${healthCarePlan.id}" var="formAction" />
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />
				   <jsp:param name="put" value="true"/>	
				   <jsp:param name="edit" value="true"/>			  	
				</jsp:include>
			</div>			
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				links.add(new Link("/convenios/novo","Adicionar novo convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>