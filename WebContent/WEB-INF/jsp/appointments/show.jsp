<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Appointment"%>

<html>
	<head>
		<title>.: EasyClinica - Visualizar Consulta :.</title>
	</head>
	<body>

		<div id="main">

			<div class="block" id="block">
			
				<h2>Paciente: ${appointment.patient.name}</h2>
				
				<helper:patientMenu patient="${appointment.patient}" selected="Consultas" /> 
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<div class="content">
				
					Data da consulta: <fmt:formatDate value="${appointment.appointmentDate.time}" pattern="dd/MM/yyyy" /> 
					Convênio: ${appointment.healthCarePlan}
					Doutor: ${appointment.doctor}
					Especialidade: ${appointment.specialty}
					Retorno? ${appointment.return}
					Observacoes: ${appointment.observations}
					
					<c:forEach items="${appointment.procedures}" var="procedure">
					
						Procedimento: ${procedure.procedure}
						Valor: ${procedure.amount}
						Fixo? ${procedure.fixedAmount}
						
						Materiais
						<c:forEach items="${procedure.materials}" var="material">
							${material.material} - qtd: ${material.qty} a R$ = ${material.unitAmount} = ${material.qty * material.unitAmount}   
						</c:forEach>
						
						<c:forEach items="${procedure.medicines}" var="medicine">
							${medicine.medicine} - qtd: ${medicine.qty} a R$ = ${medicine.unitAmount} = ${medicine.qty * medicine.unitAmount}   
						</c:forEach>
					</c:forEach>
					
					Total do procedimento: ${appointment.procedureAmount}
					Valor da consulta: ${appointment.appointmentAmount}
					Total: ${appointment.appointmentAmount + appointment.procedureAmount}
					
				</div>
				
				<div class="botoes">
					<c:url value="/pacientes/${appointment.patient.id}/consultas" var="voltar"/>
					<a class="button" href="${voltar}">
						<c:url value="/images/icons/cross.png" var="img_voltar"/>
						<img src="${img_voltar}" alt="Voltar" />Voltar
					</a>
				</div>
			</div>

		</div>
			
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/" + ((Appointment)request.getAttribute("appointment")).getPatient().getId()+"/consultas","Voltar para listagem"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
