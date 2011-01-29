<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>

<html>
	<head>
		<title>.: EasyClinica - Listagem de Consultas :.</title>
	</head>
	<body>

		<div id="main">

			<div class="block" id="block">
			
				<h2>Paciente: ${patient.name}</h2>
				
				<helper:patientMenu patient="${patient}" selected="Consultas" />
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<div class="content">
				
				<table class="table"> 
                  <tr> 
                    <th width="20%">Data</th> 
                    <th width="25%">Convênio</th>
                    <th width="25%">Médico</th>
                    <th width="20%">Especialidade</th> 
                    <th width="10%">&nbsp;</th> 
                  </tr> 
                  
					<c:forEach items="${patient.appointments}" var="appointment" varStatus="st">
					<tr class="${st.count%2==0?'odd':'even'}"> 
						<td><fmt:formatDate value="${appointment.appointmentDate.time}" pattern="dd/MM/yyyy" />
								<c:if test="${appointment.return eq true}">
									<span class="label-retorno">
										RETORNO
									</span>
								</c:if>
						</td> 
						<td>${appointment.healthCarePlan.name}</td>
						<td>${appointment.doctor.name}</td>
						<td>${appointment.specialty.name}</td>
						<td class="last" style="text-align: left;"> 
						<c:url value="/pacientes/${patient.id}/consultas/${appointment.id}" var="detailedUrl" />
						<a href="${detailedUrl}">Ver</a>
						</td>
					</tr>
					</c:forEach>
				  </table>
				</div>
				
				<div class="botoes">
					<c:url value="/pacientes/${appointment.patient.id}/consultas" var="voltar"/>
					<a class="button" href="${voltar}">
						<c:url value="/images/icons/cross.png" var="img_voltar"/>
						<img src="${img_voltar}" alt="Voltar" />Voltar
					</a>
				</div>
			</div>

		</div>
			
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId(),"Perfil do paciente"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
