<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="br.com.easyclinica.domain.entities.Position"%>
<%@page import="br.com.easyclinica.infra.multitenancy.LoggedUser"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Exibição do Paciente :.</title>
	</head>
	<body>

		<div class="box" id="pacientes">
			<helper:patientMenu patient="${patient}" selected="Paciente" />
			
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<h2>${patient.name}</h2>
				
				<fieldset>
					<div>
						<label class="title">Nome:</label>
						${patient.name}
					</div>
					
					<div class="telephone">
						<label class="title">Telefone:</label>
			    		${patient.telephone}
			    	</div>
					
					<div class="telephone">
						<label class="title">Celular:</label>
			    		${patient.cellphone}
			    	</div>
			
					<div class="telephone">
						<label class="title">Comercial:</label>
			    		${patient.commercialPhone}
			    	</div>
			
					<div class="title">
						<label class="title">Data de Nascimento:</label>
			    		<fmt:formatDate value="${patient.birthDate.time}" pattern="dd/MM/yyyy" />
			    	</div>
			
					<div class="title">
						<label class="title">RG:</label>
			    		${patient.rg}
			    	</div>
			    	
			    	<div class="title">
						<label class="title">CPF:</label>
			    		${patient.cpf}
			    	</div>
			
			    	<div class="title">
						<label class="title">Profissão:</label>
			    		${patient.profession}
			    	</div>
			
			    	<div class="title">
						<label class="title">Estado Civil:</label>
								${patient.maritalStatus.formattedName}
			    	</div>
			    	    	    	
					<div>
						<label class="title">E-mail:</label>
						${patient.email}
					</div>
					
					<div>
						<label class="title">Convênio:</label>
						${patient.healthCarePlan.name}
					</div>
				
					<div>
						<label class="title">Número da carteirinha:</label>
			    		${patient.healthCarePlanCode}
			    	</div>
				
				</fieldset>
				
				<fieldset>	
					<div class="cep">
						<label class="title">CEP:</label>
						${patient.address.postalCode}
					</div>
					
					<div>
						<label class="title">Endereço:</label>
				    	${patient.address.street}
				    </div>
					
					<div>
						<label class="title">Bairro:</label>
						${patient.address.neighborhood}
					</div>
					
					<div>
						<label class="title">Cidade:</label>
						${patient.address.city}
					</div>
					
					<div>
						<label class="title">Estado:</label>
				    	${patient.address.state}
				    </div>
				
			   		<div class="remarks">
			       		<label class="title">Observações:</label>
			        	${patient.observations}
			      	</div>
			    </fieldset>
    
					
			</div>
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes","Voltar para listagem"));
				
				LoggedUser loggedUser = (LoggedUser)request.getSession().getAttribute("loggedUser");
				if(loggedUser.getEmployee().getPosition() != Position.DOCTOR){
					links.add(new Link("/pacientes/novo","Adicionar novo paciente"));
					links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/consultas/novo","Nova consulta"));
				}
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>			
		</div>
	</body>
</html>