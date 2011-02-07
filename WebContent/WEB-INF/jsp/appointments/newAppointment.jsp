<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>

<html>
<head>
	<title>.: EasyClinica - Nova Consulta :.</title>
</head>
<body>
	<script type="text/javascript" language="javascript">
		$(function(){
			$('#aviso-retorno').hide();
		});
	</script>
	
	<div id="main" tela="consultas">
		
		<div class="block" id="block">
		
			<h2>Paciente: ${patient.name}</h2>
		
			<helper:patientMenu patient="${patient}" selected="Consultas" />
			
			<div class="content">
				<div class="inner">
					<c:url value="/pacientes/${patient.id}/consultas/novo" var="action" />
					<form action="${action}" method="post">
						<input type="hidden" value="${patient.id}" name="appointment.patient.id"/>
						
						<helper:errors errors="${errors}" />
						
						<fieldset class="cadastro consulta">
							<div class="full">
								<label class="label">Data*:</label>
								<input type="text" name="appointment.appointmentDate" class="datepicker text_field" />
							</div>
							
							<div class="agrupar_campos">
								<label class="label">Convênio*:</label>
								<input type="radio" name="appointment.healthCarePlan.id" value="${patient.healthCarePlan.id}" checked="checked" />${patient.healthCarePlan.name}
								<input type="radio" name="appointment.healthCarePlan.id" value="0" /> Particular
							</div>
							
							<div class="agrupar_campos">
								<label class="label">Retorno?</label>
								<input type="radio" name="appointment.return" value="true"> Sim
								<input type="radio" name="appointment.return" value="false" checked="checked""> Não
							</div>
							
							<div class="agrupar_campos">
								<label class="label">Médico*:</label>
								<select name="appointment.doctor.id">
									<option value="0">Selecione um médico</option>
									<c:forEach var="doctor" items="${doctors}" varStatus="status">
										<option value="${doctor.id}">${doctor.name}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="agrupar_campos">
								<label class="label">Especialidade*:</label>
								<select name="appointment.specialty.id">
									<option value="0">Selecione uma Especialidade</option>
									<c:forEach var="specialty" items="${specialties}" varStatus="status">
										<option value="${specialty.id}">${specialty.name}</option>
									</c:forEach>
								</select>
							</div>
							
							<label class="label">Observações:</label>
							<textarea name="appointment.observations" class="text_field"></textarea>
							
						</fieldset>
						
						<h3>Procedimentos</h3>
						<fieldset class="search-procedure cadastro">
							<div class="busca">
								<input type="text"  id="txt_search_procedure" class="text_field"/> 
								<span class="description">Digite o código AMB ou TUSS ou o nome do procedimento.</span>
							</div>
													
							<c:url value="/images/icons/add.png" var="img_add"/>
							<img src="${img_add}" alt="Adicionar Procedimento" id="btn_search_procedure"/>
						</fieldset>
						
						<div id="procedures"></div>
					
						<div class="appointment-amount">
							<table>
								<tr>
									<td class="title">Consulta:</td>
									<td class="valor currency" id="valor-consulta">0</td>
								</tr>
								<tr>
									<td class="title">Procedimentos:</td>
									<td class="valor currency" id="appointment-procedure-amount">0</td>
								</tr>
								<tr>
									<td class="title">Total:</td>
									<td class="valor currency" id="appointment-amount">0</td>
								</tr>
							</table>
						</div>
					
						<div class="botoes">
							<button class="button submit" type="button">
								<c:url value="/images/icons/tick.png" var="img_salvar"/>
								<img src="${img_salvar}" alt="Salvar" />Salvar
							</button>
							
							<c:url value="/pacientes" var="cancelar"/>
							<a class="button" href="${cancelar}">
								<c:url value="/images/icons/cross.png" var="img_cancelar"/>
								<img src="${img_cancelar}" alt="Cancelar" />Cancelar
							</a>
						</div>
					
					</form>
				</div>
			</div>
		</div>
			
	</div>
	
	<div id="sidebar">
		<% 
			java.util.List<Link> links = new LinkedList<Link>();  
			links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/consultas","Voltar para listagem"));
			pageContext.setAttribute("links",links);
		%>
		<helper:navigation links="${links}"></helper:navigation>
		
		<helper:notice id="aviso-retorno" notice="Essa consulta pode ser um retorno." title="Retorno" />
	</div>
	
</body>
</html>