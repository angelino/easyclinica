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

		<div class="box" id="consultas">
			<helper:patientMenu patient="${appointment.patient}" selected="Consultas" />
			
			<div class="boxcontent">
			
				<h2>Paciente: ${appointment.patient.name}</h2>
								
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
						${material.material} - qtd: ${material.qty.qty} a R$ = ${material.unitAmount.amount} = ${material.qty.qty * material.unitAmount.amount}   
					</c:forEach>
					
					<c:forEach items="${procedure.medicines}" var="medicine">
						${medicine.medicine} - qtd: ${medicine.qty.qty} a R$ = ${medicine.unitAmount.amount} = ${medicine.qty.qty * medicine.unitAmount.amount}   
					</c:forEach>
				</c:forEach>
				
				Total do procedimento: ${appointment.procedureAmount.amount}
				Valor da consulta: ${appointment.appointmentAmount.amount}
				Total: ${appointment.appointmentAmount.amount + appointment.procedureAmount.amount}
					
				<div class="boxactions">
					<a class="btnback" href="<c:url value="/pacientes/${appointment.patient.id}/consultas" />">Voltar</a>
			    </div>
			</div>

		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/" + ((Appointment)request.getAttribute("appointment")).getPatient().getId()+"/consultas","Voltar para listagem"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
