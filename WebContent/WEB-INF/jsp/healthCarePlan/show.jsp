<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.HealthCarePlan"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Visualizar Convênio :.</title>
	</head>
	<body>

		<div id="main">

			<div class="block" id="block">
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<div class="content tables">
			   		<h2 class="title">${healthCarePlan.name}</h2>
					
					<div class="inner">
				
						<label class="label">Endereço:</label> 	${healthCarePlan.address.street}
						
						<div class="agrupar_campos">
							<label class="label">Bairro:</label>
					    ${healthCarePlan.address.neighborhood}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">CEP:</label>
					    ${healthCarePlan.address.postalCode}
						</div>
				
						<div class="agrupar_campos">
							<label class="label">Cidade:</label>
					    ${healthCarePlan.address.city}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">Estado:</label>
					    ${healthCarePlan.address.state}
						</div>
							
						<label class="label">Nome do Responsável:</label>
				    	${healthCarePlan.contact}	
						<div class="agrupar_campos">
							<label class="label">Telefone*:</label>
				    	${healthCarePlan.telephone}
						</div>
						
						<div class="agrupar_campos">
							<label class="label">E-mail:</label>
				    	${healthCarePlan.email}
						</div>
						
						<label class="label">Website:</label>
				    ${healthCarePlan.website}
					
					<div class="agrupar_campos">
						<label class="label">Tabela de Serviços*:</label>
						${healthCarePlan.table.name}
					</div>
					
					<div class="agrupar_campos">
						<label class="label">Valor em R$ da CH:</label>
						${healthCarePlan.ch.money}
					</div>
					
						<label class="label">Observações</label>
				    ${healthCarePlan.observations}
					
					</div>
					
					<div class="service-rules">
						<form action="<c:url value="/convenios/${healthCarePlan.id}/service-rules" />" method="post" id="service_form">
							<table class="table">
								<tr>
									<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_convenios"/></th>
									<th>Serviço</th>
									<th>CH?</th>
									<th>Valor?</th>
									<th class="last">&nbsp;</th>
								</tr>
								<c:forEach var="rule" items="${healthCarePlan.serviceRules}">
								<tr>
									<td></td>
									<td>${rule.service.name}</td>
									<td>
										<c:choose>
											<c:when test="${rule.rulingCh}">
											${rule.ch}
											</c:when>
											<c:otherwise>
											-
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${rule.rulingValue}">
											R$ ${rule.value}
											</c:when>
											<c:otherwise>
											-
											</c:otherwise>
										</c:choose>
									</td>							
									<td> 
											<a href="<c:url value="/convenios/${healthCarePlan.id}/service-rules/${rule.id}" />">deletar</a>
									</td>
								</tr>
								</c:forEach>
								<tr>
									<td></td>
									<td>
										<select name="service.id">
											<c:forEach var="service" items="${healthCarePlan.servicesWithNoRules}">
											<option value="${service.id}">${service.name}</option>
											</c:forEach>
										</select>
									</td>
									<td><input type="text" name="ch.ch" /></td>
									<td><input type="text" name="value.money" /></td>
									<td>
										<button class="button" type="submit">
											<c:url value="/images/icons/tick.png" var="img_salvar"/>
											<img src="${img_salvar}" alt="Salvar" />Salvar
										</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div class="material-rules">
						<form action="<c:url value="/convenios/${healthCarePlan.id}/material-rules" />" method="post" id="material_form">
							<table class="table">
								<tr>
									<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_convenios"/></th>
									<th>Material</th>
									<th>CH?</th>
									<th>Valor?</th>
									<th class="last">&nbsp;</th>
								</tr>
								<c:forEach var="rule" items="${healthCarePlan.materialRules}">
								<tr>
									<td></td>
									<td>${rule.material.name}</td>
									<td>
										<c:choose>
											<c:when test="${rule.rulingCh}">
											${rule.ch}
											</c:when>
											<c:otherwise>
											-
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${rule.rulingValue}">
											R$ ${rule.value}
											</c:when>
											<c:otherwise>
											-
											</c:otherwise>
										</c:choose>
									</td>							
									<td>
										<a href="<c:url value="/convenios/${healthCarePlan.id}/material-rules/${rule.id}" />">deletar</a>
									</td>
								</tr>
								</c:forEach>
								<tr>
									<td></td>
									<td>
										<select name="material.id">
											<c:forEach var="material" items="${healthCarePlan.materialsWithNoRules}">
											<option value="${material.id}">${material.name}</option>
											</c:forEach>
										</select>
									</td>
									<td><input type="text" name="ch.ch" /></td>
									<td><input type="text" name="value.money" /></td>
									<td>
										<button class="button" type="submit">
											<c:url value="/images/icons/tick.png" var="img_salvar"/>
											<img src="${img_salvar}" alt="Salvar" />Salvar
										</button>
									</td>
								</tr>
							</table>
						</form>
					</div>	
					
				</div>
				<!-- FIM CONTENT  -->
				
			</div>
		</div>	
		<!-- FIM MAIN -->
		
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios","Voltar para listagem"));
				links.add(new Link("/convenios/" + ((HealthCarePlan)request.getAttribute("healthCarePlan")).getId()+"/editar","Editar o convênio"));
				links.add(new Link("/convenios/novo","Adicionar novo convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>		
		</div>
	</body>
</html>
