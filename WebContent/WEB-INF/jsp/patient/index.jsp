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

		<div class="box" id="pacientes">
			<div class="boxcontent">
			    <h2>Listagem de Pacientes</h2>
			    	
			    <helper:message successKey="${successKey}" errorKey="${errorKey}" />
			    	
		    	<c:choose>
		    		<c:when test="${fn:length(patients.result) == 0}">
		    			<p class="messengernotice">
		    				Não há pacientes cadastrados! <a href='<c:url value="/pacientes/novo" />'>Clique aqui</a> para adicionar o primeiro!
		    			</p>
		    		</c:when>
		    		<c:otherwise>
			    		<table border="0">
							<tr class="tableheader">
								<th width="65px">Status:</th>
								<th>Nome</th>
								<th>Convênio</th>
								<th>Número no Convênio</th>
								<th>Telefone</th>
								<th>Celular</th>
								<th width="145px">&nbsp;</th>
							</tr>
							
							<c:forEach var="patient" items="${patients.result}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									<c:choose>
										<c:when test="${patient.active}">
											<td class="statusenable">Ativo</td>
                                    		<td>${patient.name}</td>
										</c:when>
										<c:otherwise>
											<td class="statusdisable">Inativo</td>
                                    		<td class="namedisable">${patient.name}</td>
										</c:otherwise>
									</c:choose>
									
									<td>${patient.healthCarePlan.name}</td>
									<td>${patient.healthCarePlanCode}</td>
									<td>${patient.telephone}</td>
									<td>${patient.cellphone}</td>
									
									<td class="buttons">
										<a class="btnpeopleedit" title="Editar" href="<c:url value="/pacientes/${patient.id}/editar" />">&nbsp;</a>
                                        <a class="btnpeopleshow exibir" title="Exibir" patient_id="${patient.id}">&nbsp;</a>
                                        
                                        <c:choose>
											<c:when test="${patient.active}">
												<a class="btnpeopledisable last" title="Desativar" href="<c:url value="/pacientes/${patient.id}/deactivate" />">&nbsp;</a>
											</c:when>
											<c:otherwise>
												<a class="btnpeopleenable last" title="Ativar" href="<c:url value="/pacientes/${patient.id}/activate" />">&nbsp;</a>
											</c:otherwise>
										</c:choose>
                                    </td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${patients.totalPages}" current="${patients.currentPage}" />
					
		    		</c:otherwise>
		    	</c:choose>
			</div>	
		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/pacientes/novo","Criar novo paciente"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>