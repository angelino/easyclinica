<%@page import="br.com.easyclinica.domain.entities.Anamnese"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="br.com.easyclinica.domain.entities.Position"%>
<%@page import="br.com.easyclinica.domain.entities.GeneralObservations" %>
<%@page import="br.com.easyclinica.infra.multitenancy.LoggedUser"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Observações :.</title>
	</head>
	<body>

		<div class="box" id="observacoes" main-page="pacientes">
			<helper:patientMenu patient="${patient}" selected="Observacoes" />
		
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			
		   		<h2>Observação em <fmt:formatDate value="${observation.date.time}" pattern="dd/MM/yyyy" /></h2>				

				<fieldset>
			      	<div class="date">
						<label class="title">Data:</label>
						<fmt:formatDate value="${observation.date.time}" pattern="dd/MM/yyyy" />
					</div>
			      	<div class="remarks">
			          	<label class="title">Observações:</label>
			            ${observation.text}
			        </div>
  				</fieldset>
			</div>			
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/observacoes/" + ((GeneralObservations)request.getAttribute("observation")).getId() + "/editar","Editar observação"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/observacoes/novo","Criar nova observação"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/observacoes","Voltar para observações"));
				
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