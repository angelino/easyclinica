<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Editar Médico :.</title>
	</head>
	<body>

		<div class="box" id="medicos">
			<div class="boxcontent">
		   		<h2>Editar Médico</h2>				
		
				<c:url value="/medicos/${doctor.id}" var="formAction" />			
				<jsp:include page="_form.jsp">	
				   <jsp:param name="formAction" value="${formAction}" />
				   <jsp:param name="put" value="true"/>			  	
				</jsp:include>
			</div>			
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>(); 
				links.add(new Link("/medicos/novo","Criar novo médico"));
				links.add(new Link("/medicos","Voltar para listagem"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	
	</body>
</html>