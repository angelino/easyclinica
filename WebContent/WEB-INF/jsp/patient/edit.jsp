<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Editar Paciente :.</title>
	</head>
	<body>

		<div id="main">

			<div class="block" id="block">
				
				<div class="content">
			   		<h2 class="title">Editar Paciente</h2>
					<div class="inner">
			
						<c:url value="/pacientes/${patient.id}" var="formAction" />
						
						<jsp:include page="_form.jsp">	
						   <jsp:param name="formAction" value="${formAction}" />
						   <jsp:param name="put" value="true"/>	
						   <jsp:param name="edit" value="true"/>			  	
						</jsp:include>
					</div>
				</div>
			</div>
			
		</div>
			
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes","Voltar para listagem"));
				links.add(new Link("/pacientes/"+ ((Patient)request.getAttribute("patient")).getId(),"Exibir o paciente"));
				links.add(new Link("/pacientes/novo","Adicionar novo paciente"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			
		</div>
	</body>
</html>