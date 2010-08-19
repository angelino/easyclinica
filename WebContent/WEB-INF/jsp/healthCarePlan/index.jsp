<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<!--  div class="secondary-navigation">
				  <ul class="wat-cf">
				    <li class="first"><a href="javascript:void(0);">Ativos</a></li>
				    <li class="active"><a href="javascript:void(0);">Inativos</a></li>
				  </ul>
				</div -->
			    <div class="content">
			    	<h2 class="title">Listagem de Convênios</h2>
			    	
			    	<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			    	
			        <div class="inner">
						<table class="table">
							<tr>
								<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_convenios"/></th>
								<th>Nome</th>
								<th>Contato</th>
								<th>Telefone</th>
								<th class="last">&nbsp;</th>
							</tr>
							
							<c:forEach var="healthCare" items="${healthcares.result}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									<td><input type="checkbox" class="checkbox" rel="chk_convenios" name="id" value="${healthCare.id}" /></td>
									<td id="name_${healthCare.id}">
										<c:choose>
											<c:when test="${healthCare.active.active}">
												${healthCare.name}
											</c:when>
											<c:otherwise>
												<span class="deactivated-item">${healthCare.name}</span> (inativo)
											</c:otherwise>
										</c:choose>									
									</td>
									<td>${healthCare.contact}</td>
									<td>${healthCare.telephone}</td>
									<td>
										<a href="<c:url value="/convenios/${healthCare.id}/editar" />">editar</a> 
										| <a href="<c:url value="/convenios/${healthCare.id}" />">exibir</a> 
										<c:if test="${healthCare.active.active}">
										<span>| <a href="<c:url value="/convenios/${healthCare.id}" />" rel="healthcare" class="delete">inativar</a></span>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${healthcares.totalPages}" current="${healthcares.currentPage}" />
					</div>
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