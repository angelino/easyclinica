<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Listagem de Médicos :.</title>
	</head>
	<body>
		
   		<div class="box" id="medicos">
		
			<div class="boxcontent">
				<h2>Listagem de Médicos</h2>
			    	
			    <helper:message successKey="${successKey}" errorKey="${errorKey}" />
			    	
		    	<c:choose>
		    		<c:when test="${fn:length(doctors.result) == 0}">
		    			<p class="messengernotice">
		    				Não há médicos cadastrados! <a href='<c:url value="/medicos/novo" />'>Clique aqui</a> para adicionar o primeiro!
		    			</p>
		    		</c:when>
		    		<c:otherwise>
			        	<table border="0">
							<tr class="tableheader">
								<th width="65px">Status:</th>
								<th>Nome:</th>
								<th>CRM:</th>
								<th>Especialidade:</th>
								<th>Telefone:</th>
								<th>E-mail:</th>
								<th width="145px">&nbsp;</th>
							</tr>
							
							<c:forEach var="doctor" items="${doctors.result}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									
									<c:choose>
										<c:when test="${doctor.active}">
											<td class="statusenable">Ativo</td>
                                    		<td>${doctor.name}</td>
										</c:when>
										<c:otherwise>
											<td class="statusdisable">Inativo</td>
                                    		<td class="namedisable">${doctor.name}</td>
										</c:otherwise>
									</c:choose>
									<td>${doctor.crm}</td>
									<td>${doctor.specialty.name}</td>
									<td>${doctor.telephone}</td>
									<td>${doctor.email}</td>
									<td class="buttons">
										<a class="btnpeopleedit" title="Editar" href="<c:url value="/medicos/${doctor.id}/editar" />">&nbsp;</a>
                                        <a class="btnpeopleshow exibir" title="Exibir" doctor_id="${doctor.id}">&nbsp;</a>
                                        
                                        <c:choose>
											<c:when test="${doctor.active}">
												<a class="btnpeopledisable last" title="Desativar" href="<c:url value="/medicos/${doctor.id}/deactivate" />">&nbsp;</a>
											</c:when>
											<c:otherwise>
												<a class="btnpeopleenable last" title="Ativar" href="<c:url value="/medicos/${doctor.id}/activate" />">&nbsp;</a>
											</c:otherwise>
										</c:choose>
                                    </td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${doctors.totalPages}" current="${doctors.currentPage}" />
					
					</c:otherwise>
				</c:choose>
			</div>
			
		</div>
					
		<div class="boxright">			
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/medicos/novo","Criar novo médico"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>   
	    </div>
	</body>
</html>