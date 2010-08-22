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
		<!-- INÍCIO MAIN -->
   		<div id="main">
		
			<div class="block" id="block-tables">
			
				<div class="content">
			    	<h2 class="title">Listagem de Médicos</h2>
			    	
			    	<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			    	
			    	<c:choose>
			    		<c:when test="${fn:length(doctors.result) == 0}">
			    			<div class="inner">
			    			Não há médicos cadastrados! <a href='<c:url value="/medicos/novo" />'>Clique aqui</a> para adicionar o primeiro!
			    			</div>
			    		</c:when>
			    		<c:otherwise>
			        <div class="inner">
						<table class="table">
							<tr>
								<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_medicos"/></th>
								<th>Nome</th>
								<th>CRM</th>
								<th>Telefone</th>
								<th>E-mail</th>
								<th class="last">&nbsp;</th>
							</tr>
							
							<c:forEach var="doctor" items="${doctors.result}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									<td><input type="checkbox" class="checkbox" rel="chk_medicos" name="id" value="${doctor.id}" /></td>
									<td id="name_${doctor.id}">
										<c:choose>
											<c:when test="${doctor.active.active}">
												${doctor.name}
											</c:when>
											<c:otherwise>
												<span class="deactivated-item">${doctor.name}</span> (inativo)
											</c:otherwise>
										</c:choose>
									</td>
									<td>${doctor.crm}</td>
									<td>${doctor.telephone}</td>
									<td>${doctor.email}</td>
									<td>
										<a href="<c:url value="/medicos/${doctor.id}/editar" />">editar</a> 
										| <a href="<c:url value="/medicos/${doctor.id}" />">exibir</a> 
										<c:if test="${doctor.active.active}">
										<span>| <a href="<c:url value="/medicos/${doctor.id}" />" rel="doctor" class="delete">inativar</a></span>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${doctors.totalPages}" current="${doctors.currentPage}" />
					</div>
				
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
		</div>
		<!-- FIM MAIN -->
			
		<!-- INÍCIO SIDEBAR -->
		<div id="sidebar">			
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/medicos/novo","Criar novo médico"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>   
	    </div>
		<!-- FIM SIDEBAR -->
	</body>
</html>