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

		<div class="box" id="pacientes">
			<helper:patientMenu patient="${patient}" selected="Paciente" />
			
			<div class="boxcontent">
				<h2>Editar Paciente</h2>
					
				<c:url value="/pacientes/${patient.id}" var="formAction" />
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
				links.add(new Link("/pacientes","Voltar para listagem"));
				links.add(new Link("/pacientes/novo","Adicionar novo paciente"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/consultas/novo","Nova consulta"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>			
		</div>
	</body>
</html>