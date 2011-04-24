<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.HealthCarePlan"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Exibição do Convênio :.</title>
	</head>
	<body>

		<div class="box" id="convenios">			
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
  				<h2>Convênio: ${healthCarePlan.name}</h2>
	
				<fieldset>
					<div>
						<label class="title">Valor em R$ da CH:</label>
						<span class="currency">${healthCarePlan.ch}</span>
					</div>
					<div>
						<label class="title">Período para retorno (em dias):</label>
						${healthCarePlan.periodToReturn}
					</div>					
					<div>
						<label class="title">Nome do Responsável:</label>
						${healthCarePlan.contact}
					</div>					
					<div>
						<label class="title">Telefone:</label>
						${healthCarePlan.telephone}
					</div>
					<div>
						<label class="title">E-mail:</label>
						${healthCarePlan.email}
					</div>
					<div>
						<label class="title">Website:</label>
						${healthCarePlan.website}
					</div>
					<div>
						<label class="title">Endereço:</label>
						<c:if test="${!empty healthCarePlan.address.street}">
							${healthCarePlan.address.street}<br/>
							${healthCarePlan.address.postalCode} - ${healthCarePlan.address.neighborhood}<br/>
							${healthCarePlan.address.city} - ${healthCarePlan.address.state}
						</c:if>
					</div>									
					<div class="remarks">
			       		<label class="title">Observações:</label>
			        	${healthCarePlan.observations}
			      	</div>
				</fieldset>
	
			</div>
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				links.add(new Link("/convenios/novo","Adicionar novo convênio"));
				links.add(new Link("/convenios/" + ((HealthCarePlan)request.getAttribute("healthCarePlan")).getId() + "/editar","Alterar este convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>			
		</div>
	</body>
</html>
	
		
