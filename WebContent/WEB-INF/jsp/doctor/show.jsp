<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Doctor"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Exibição do Paciente :.</title>
	</head>
	<body>

		<div class="box" id="medicos">			
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
  				<h2>Médico: ${doctor.name}</h2>
	
				<fieldset>
					<div>
						<label class="title">CRM:</label>
						${doctor.crm}
					</div>
					<div>
						<label class="title">Espeialidade:</label>
						${doctor.specialty}
					</div>					
					<div class="telephone">
						<label class="title">Telefone:</label>
						${doctor.telephone}
					</div>					
					<div>
						<label class="title">E-mail:</label>
						${doctor.email}
					</div>					
					<div class="remarks">
			       		<label class="title">Observações:</label>
			        	${doctor.observations}
			      	</div>
				</fieldset>
	
			</div>
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/medicos","Voltar para listagem"));
				links.add(new Link("/medicos/novo","Adicionar novo paciente"));
				links.add(new Link("/medicos/" + ((Doctor)request.getAttribute("doctor")).getId() + "/editar","Alterar este médico"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>			
		</div>
	</body>
</html>
	
		
