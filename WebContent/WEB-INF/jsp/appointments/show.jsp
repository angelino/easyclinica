<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Appointment"%>

<html>
	<head>
		<title>.: EasyClinica - Visualizar Consulta :.</title>
	</head>
	<body>

		<div class="box" id="consultas" main-page="pacientes">
			<helper:patientMenu patient="${appointment.patient}" selected="Consultas" />
			
			<div class="boxcontent">
			
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<h2>Paciente: ${appointment.patient.name}</h2>
				
				<div class="dados-consulta">
					<div>
						<p>Data da consulta:</p>
						<span><fmt:formatDate value="${appointment.appointmentDate.time}" pattern="dd/MM/yyyy" /></span>
					</div>
									
					<div>
						<p>Convênio:</p>
						<span>${appointment.healthCarePlan}</span>
					</div>
					 
					<div>
						<p>Doutor:</p>
						<span>${appointment.doctor}</span>
					</div>
					
					<div>
						<p>Especialidade:</p>
						<span>${appointment.specialty}</span>
					</div>
					
					<div>
						<p>Retorno?</p>
						<span>
							<c:choose>
								<c:when test="${appointment.return}">SIM</c:when>
								<c:otherwise>NÃO</c:otherwise>
							</c:choose>
						</span>
					</div>
					
					<div class="observations">
						<p>Observações</p> 
						<span>${appointment.observations}</span>
					</div>
				</div>
			</div>
			
			<div class="boxcontent">	
				<table class="tableprocedures" border="0">
					<c:forEach items="${appointment.procedures}" var="procedure">
						<tr class="tableheader">
						    <th>Código:</th>
						    <th>Procedimento:</th>
						    <th colspan="2">Valor CH:</th>
						    <th width="100px">Valor R$</th>
						    <th width="95px">&nbsp;</th>
						</tr>
						<tr>
						    <td>
								<c:if test="${not empty procedure.procedure.ambCode}">
									${procedure.procedure.ambCode}(AMB)
								</c:if>
								<c:if test="${not empty procedure.procedure.tussCode}">
									${procedure.procedure.tussCode}(TUSS)
								</c:if>
							</td>
							
						    <td>${procedure.procedure.name}</td>
						    <td colspan="2" class="center">
						    	<c:choose>
							    	<c:when test="${procedure.fixedAmount}">
							    		-
							    	</c:when>
							    	<c:otherwise>
							    		${procedure.ch} CH
							    	</c:otherwise>
						    	</c:choose>
						    </td>
						    <td class="currency">${procedure.amount}</td>
						    <td>&nbsp;</td>
						</tr>
						
						<c:forEach items="${procedure.assistants}" var="assistant">
							<tr>
								<td>&nbsp;</td>
	                            <td>${assistant.name} (${assistant.type.formattedName})</td>
	                            <td colspan="2" class="center">${assistant.ch} CH</td>
	                            <td class="currency">${assistant.amount}</td>
	                            <td>&nbsp;</td>
	                        </tr>
						</c:forEach>
						
						<tr class="tableheader">
						    <td rowspan="${ 3 + fn:length(procedure.materials) + fn:length(procedure.medicines)}" class="tablenostyle">&nbsp;</td>
						    <td>Material/Medicamento:</td>
						    <td>Quantidade:</td>
						    <td>Valor Unitário:</td>
						    <td>Total:</td>
						    <td>&nbsp;</td>
						</tr>
						
						<c:forEach items="${procedure.materials}" var="material">
							<c:set var="materialAmount" value="${material.qty * material.unitAmount}" />
							<tr>
	                            <td>${material.material}</td>
	                            <td>${material.qty}</td>
	                            <td class="currency">${material.unitAmount}</td>
	                            <td class="currency">${materialAmount}</td>
	                            <td>&nbsp;</td>
	                        </tr>
						</c:forEach>
					
						<c:forEach items="${procedure.medicines}" var="medicine">
							<c:set var="medicineAmount" value="${medicine.qty * medicine.unitAmount}" />
							<tr>
	                            <td>${medicine.medicine}</td>
	                            <td>${medicine.qty}</td>
	                            <td class="currency">${medicine.unitAmount}</td>
	                            <td class="currency">${medicineAmount}</td>
	                            <td>&nbsp;</td>
	                        </tr>
						</c:forEach>					
						<tr class="boxsubtotal">
						    <td colspan="3">Total do procedimento:</td>
						    <td class="currency">${procedure.totalAmount}</td>
						    <td>&nbsp;</td>
						</tr>
						<tr class="boxdivisortable">
							<td colspan="5">&nbsp;</td>
						</tr>
					</c:forEach> 
					
					<tr class="boxtotal">
						<td colspan="1" rowspan="7" class="tablenostyle">&nbsp;</td>
						<th colspan="2" rowspan="7">&nbsp;</th>
						<td colspan="1">Materiais:</td>
						<td class="currency">${appointment.materialAmount}</td>
						<td rowspan="7">&nbsp;</td>
					</tr>
					<tr class="boxtotal">
						<td colspan="1">Medicamentos:</td>
						<td class="currency">${appointment.medicineAmount}</td>
					</tr>
					<tr class="boxtotal">
						<td colspan="1">Auxiliares:</td>
						<td class="currency">${appointment.assistantAmount}</td>
					</tr>
					<tr class="boxtotal">
						<td colspan="1">Procedimentos:</td>
						<td class="currency">${appointment.procedureAmount}</td>
					</tr>
					<tr class="boxtotal">
						<td colspan="1">Consulta:</td>
						<td class="currency">${appointment.appointmentAmount}</td>
					</tr>
					<tr class="boxtotal">
						<td colspan="1">Taxa de Sala:</td>
						<td class="currency">${appointment.roomRateAmount}</td>
					</tr>
					<tr class="boxtotal">
						<td colspan="1">Total:</td>
						<td class="currency">${appointment.totalAmount}</td>
					</tr>
				</table>
			</div>

		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/" + ((Appointment)request.getAttribute("appointment")).getPatient().getId()+"/consultas/novo","Nova consulta"));
				links.add(new Link("/pacientes/" + ((Appointment)request.getAttribute("appointment")).getPatient().getId()+"/consultas","Voltar para listagem de consultas"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:patientDetails patient="${patient}" />
		</div>
	</body>
</html>
