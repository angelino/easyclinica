<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>

<html>
	<head>
		<title>.: EasyClinica - Listagem de Consultas :.</title>
	</head>
	<body>

		<div class="box" id="consultas">
			<helper:patientMenu patient="${patient}" selected="Consultas" />
			
			<div class="boxcontent">			
				<h2>Listagem de Consultas - ${patient.name}</h2>
				
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
				<c:choose>
		    		<c:when test="${fn:length(patient.appointments) == 0}">
		    			<p class="messengernotice">
		    				Não há consultas registradas para esse paciente! <a href='<c:url value="/pacientes/${patient.id}/consultas/novo" />'>Clique aqui</a> para cadastrar uma consulta!
		    			</p>
		    		</c:when>
		    		<c:otherwise>				
						<table border="0" class="easy">
							<tr class="tableheader">
			                    <th>Data</th> 
			                    <th>Convênio</th>
			                    <th>Médico</th>
			                    <th>Especialidade</th> 
			                    <th width="145px">&nbsp;</th>
			                </tr> 
                  
							<c:forEach items="${patient.appointments}" var="appointment" varStatus="st">
								<tr class="${st.count%2==0?'odd':'even'}"> 
									<td>
										<fmt:formatDate value="${appointment.appointmentDate.time}" pattern="dd/MM/yyyy" />
										<c:if test="${appointment.return eq true}">
											<span class="retorno">RETORNO</span>
										</c:if>
									</td> 
									<td>${appointment.healthCarePlan.name}</td>
									<td>${appointment.doctor.name}</td>
									<td>${appointment.specialty.name}</td>
									
									<td class="buttons">
										<a class="btnpeopleshow" title="Exibir" href="<c:url value="/pacientes/${patient.id}/consultas/${appointment.id}"/>">&nbsp;</a>
                                    </td>
								</tr>
							</c:forEach>
				  		</table>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/editar","Perfil do paciente"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/consultas/novo","Nova consulta"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
