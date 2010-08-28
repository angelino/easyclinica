<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Visualizar Paciente :.</title>
		<script type="text/javascript" language="javascript">
			$(function(){
				$('#novaConsulta').dialog({
					autoOpen: false,
					height: 400,
					width: 650,
					modal: true
				});

				$('#btnNovaConsulta').click(function() {
					$('#novaConsulta').dialog('open');
				});

				$('#btnSalvarConsulta').click(function() {
					$('#novaConsulta').dialog('close');
				});

				$('#data-consulta').datepicker();

			});
		</script>
	</head>
	<body>

		<div id="main">

			<div class="secondary-navigation"> 
	            <ul class="wat-cf abas"> 
	              <li class="first"><a href="#dados-gerais">Dados Gerais</a></li> 
	              <li><a href="#consultas">Consultas</a></li> 
	            </ul> 
	        </div>

			<div class="block" id="dados-gerais">
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<div class="content dados">
			   		<h2 class="title">${patient.name}</h2>
					
					<div class="inner full">
				
						<p class="info">
							<label class="label">Endereço:</label> ${patient.address.street}
						</p>
						
						<div class="agrupar_campos">
							<label class="label">CEP:</label>  ${patient.address.postalCode}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Bairro:</label> ${patient.address.neighborhood}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Cidade:</label> ${patient.address.city}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Estado:</label> ${patient.address.state}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Telefone*:</label> ${patient.telephone}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Celular:</label> ${patient.cellphone}
						</div>

						<div class="agrupar_campos">
							<label class="label">Convênio:</label> ${patient.healthCarePlan.name}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Número da carteirinha:</label> ${patient.healthCareId}
						</div>
						
						<p class="info">
							<label class="label">E-mail:</label> ${patient.email}
						</p>
					
						<p class="info">
							<label class="label observacoes">Observações</label> ${patient.observations}
						</p>
					</div>
				</div>
			</div>
			
			<div class="block" id="consultas">
				<div class="content tables">
					<h2 class="title">${patient.name}</h2>
				
					<div class="botoes">
						<button class="button" id="btnNovaConsulta">
							<c:url value="/images/icons/tick.png" var="img_salvar"/>
							<img src="${img_salvar}" alt="Nova consulta" />Nova consulta
						</button>
					</div>

						<table class="table">
							<tr>
								<th>Data</th>
								<th>Convênio</th>
								<th>Observações</th>
								<th class="last">&nbsp;</th>
							</tr>
							<tr class="odd">
								<td>10/10/2010</td>
								<td>Amil</td>
								<td>Paciente apresentou febre e ...</td>
								<td>exibir | apagar</td>
							</tr>
							<tr class="even">
								<td>10/10/2010</td>
								<td>Amil</td>
								<td>Paciente apresentou febre e ...</td>
								<td>exibir | apagar</td>
							</tr>
						</table>
				</div>
				
			</div>
		</div>
		
		<div id="novaConsulta" title="Nova consulta">
			<div>
				Médico:
				<select name="medico">
					<option value="1">Dr. X</option>
					<option value="1">Dr. Y</option>
				</select>
			</div>		
			<div>
				Convênio:
				<select name="convenio">
					<option value="1">amil</option>
					<option value="1">amil</option>
				</select>
			</div>	
			<div>Data: <input type="text" name="data" id="data-consulta" /></div>
			<table class="table">
				<tr>
					<th>Qtd.</th>
					<th>Procedimento/Material</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<td>1</td>
					<td>Raio X (cód 1234-5)</td>
					<td>X</td>
				</tr>
				<tr>
					<td>1</td>
					<td>Gesso (cód 1234-6)</td>
					<td>X</td>
				</tr>
				<tr>
					<td><input type="text" name="qtd" style="width:30px" /></td>
					<td><input type="text" name="procedimento" style="width:350px;" /></td>
					<td>Salvar</td>
				</tr>
			</table>
			
			<div>
				<button class="button" id="btnSalvarConsulta">
					<c:url value="/images/icons/tick.png" var="img_salvar"/>
					<img src="${img_salvar}" alt="Concluir consulta" />Concluir consulta
				</button>
			</div>
		</div>	
		
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes","Voltar para listagem"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId()+"/editar","Editar o pacientes"));
				links.add(new Link("/pacientes/novo","Adicionar novo paciente"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>		
		</div>
	</body>
</html>
