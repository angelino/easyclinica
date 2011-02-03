<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Listagem de Convênios :.</title>
	</head>
	<body>

		<div id="main">
		
			<div class="block" id="block-tables">
			    <div class="content">
			    	<h2 class="title">Listagem de Convênios</h2>
			    	
			    	<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			 			    	
			    	<c:choose>
			    		<c:when test="${fn:length(healthcares.result) == 0}">
			    			<div class="inner">
			    			Não há convênios cadastrados! <a href='<c:url value="/convenios/novo" />'>Clique aqui</a> para adicionar o primeiro!
			    			</div>
			    		</c:when>
			    		<c:otherwise>   	
			        <div class="inner">
						<table class="table">
							<tr>
								<th class="first" witdh="40%">Nome</th>
								<th width="20%">Contato</th>
								<th width="20%">Telefone</th>
								<th width="20%" class="last">&nbsp;</th>
							</tr>
							
							<c:forEach var="healthCare" items="${healthcares.result}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									<td id="name_${healthCare.id}">${healthCare.name}
										<c:choose>
											<c:when test="${not healthCare.active}">
												<span class="label-inativo">INATIVO</span>
											</c:when>
										</c:choose>									
									</td>
									<td>${healthCare.contact}</td>
									<td>${healthCare.telephone}</td>
									<td>
										<a href="<c:url value="/convenios/${healthCare.id}/editar" />">editar</a> 
										<c:if test="${healthCare.active}">
										<span>| <a href="<c:url value="/convenios/${healthCare.id}/deactivate" />" rel="healthcare" class="delete">inativar</a></span>
										</c:if>
										<c:if test="${not healthCare.active}">
										<span>| <a href="<c:url value="/convenios/${healthCare.id}/activate" />" rel="healthcare" class="delete">ativar</a></span>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${healthcares.totalPages}" current="${healthcares.currentPage}" />
					</div>
				
					</c:otherwise>
				</c:choose>
				</div>
			</div>
	
		</div>
			
		<div id="sidebar">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/convenios/novo","Criar novo convênio"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>

		</div>
	</body>
</html>