<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Listagem de Pacientes :.</title>
	</head>
	<body>

		<div id="main">
		
			<div class="block" id="block-tables">
			    <div class="content">
			    	<h2 class="title">Listagem de Pacientes</h2>
			    	
			    	<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			    	
			    	<c:choose>
			    		<c:when test="${fn:length(patients.result) == 0}">
			    			<div class="inner">
			    			Não há pacientes cadastrados! <a href='<c:url value="/pacientes/novo" />'>Clique aqui</a> para adicionar o primeiro!
			    			</div>
			    		</c:when>
			    		<c:otherwise>
			    					    	
			        <div class="inner">
						<table class="table">
							<tr>
								<th>Nome</th>
								<th>Convênio</th>
								<th>Número no Convênio</th>
								<th>Telefone</th>
								<th>Celular</th>
								<th class="last">&nbsp;</th>
							</tr>
							
							<c:forEach var="patient" items="${patients.result}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									<td id="name_${patient.id}">${patient.name}</td>
									<td>${patient.healthCarePlan.name}</td>
									<td>${patient.healthCareId}</td>
									<td>${patient.telephone}</td>
									<td>${patient.cellphone}</td>
									<td>
										<a href="<c:url value="/pacientes/${patient.id}/editar" />">editar</a> 
										| <a href="<c:url value="/pacientes/${patient.id}" />">exibir</a> 
									</td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${patients.totalPages}" current="${patients.currentPage}" />
					</div>

			    		</c:otherwise>
			    	</c:choose>

				</div>
			</div>
	
		</div>
			
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/novo","Criar novo paciente"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>

		</div>
	</body>
</html>