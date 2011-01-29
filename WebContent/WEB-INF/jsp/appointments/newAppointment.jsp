<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>.: EasyClinica - Nova Consulta :.</title>
</head>
<body>
	<!-- INÍCIO MAIN -->
	<div class="main" id="consultas">
		
		<div class="block" id="block-tables">
			<div class="content">
				
				<!-- INÍCIO INNER -->
				<div class="inner">
					<c:url value="/pacientes/${patient.id}/consultas/novo" var="action" />
					<form action="${action}" method="post">
						
						<h3>Paciente: ${patient.name}</h3>
						
						<!-- INÍCIO CADASTRO CONSULTA -->
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
								<input type="radio" name="appointment.return" value="1"> Sim
								<input type="radio" name="appointment.return" value="0" checked="checked""> Não
							</div>
							
							<div class="agrupar_campos">
								<label class="label">Médico*:</label>
								<select name="appointment.doctor.id">
									<c:forEach var="doctor" items="${doctors}" varStatus="status">
										<option value="${doctor.id}">${doctor.name}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="agrupar_campos">
								<label class="label">Especialidade*:</label>
								<select name="appointment.specialty.id">
									<c:forEach var="specialty" items="${specialties}" varStatus="status">
										<option value="${specialty.id}">${specialty.name}</option>
									</c:forEach>
								</select>
							</div>
							
							<label class="label">Observações:</label>
							<textarea name="appointment.observations" class="text_field"></textarea>
							
						</fieldset>
						<!-- FIM CADASTRO CONSULTA -->
					
						<!-- INÍCIO PROCEDIMENTOS -->
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
						<!-- FIM PROCEDIMENTOS -->	
					
						<div class="appointment-amount">
							<table>
								<tr>
									<td class="title">Consulta:</td>
									<td class="valor currency" id="valor-consulta">10</td>
								</tr>
								<tr>
									<td class="title">Procedimentos:</td>
									<td class="valor currency" id="appointment-procedure-amount"></td>
								</tr>
								<tr>
									<td class="title">Total:</td>
									<td class="valor currency" id="appointment-amount"></td>
								</tr>
							</table>
						</div>
					
						<div class="botoes">
							<button class="button" type="submit">
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
				<!-- FIM INNER -->
			</div>
		</div>
			
	</div>
	<!-- FIM MAIN -->
</body>
</html>