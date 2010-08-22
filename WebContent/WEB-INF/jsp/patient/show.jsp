<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Visualizar Paciente :.</title>
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
				
				</div>
				
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
