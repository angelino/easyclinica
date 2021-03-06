<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="br.com.easyclinica.domain.entities.Position"%>
<%@page import="br.com.easyclinica.infra.multitenancy.LoggedUser"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box" id="pacientes">		
		<helper:patientMenu patient="${patient}" selected="Impressos" />
		
			<div class="boxcontent">
				
				<h2>Impressos</h2>
				
				<ul class="printings">
					<li>
						<a href="<c:url value="/pacientes/${patient.id}/impressos/anamnese" />" class="btn">Anamnese</a>
					</li>
					<li>
						<a href="<c:url value="/pacientes/${patient.id}/impressos/atestado-de-saude" />" class="btn">Prática de Atividades Físicas</a>
					</li>
					<li>
						<a href="<c:url value="/pacientes/${patient.id}/impressos/atestado-de-saude-ocupacional" />" class="btn">Atestado de Saúde Ocupacional</a>
					</li>
				</ul>
				
			</div>
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();
				
				LoggedUser loggedUser = (LoggedUser)request.getSession().getAttribute("loggedUser");
				if(loggedUser.getEmployee().getPosition() != Position.DOCTOR){
					links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/editar","Editar paciente"));	
				}
				
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		
			<helper:patientDetails patient="${patient}" />
		</div>
	</body>
</html>