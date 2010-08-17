<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>


<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%><html>
	<head>
		<title>.: EasyClinica - Listagem de Médicos :.</title>
	</head>
	<body>
		<!-- INÍCIO MAIN -->
   		<div id="main">
		
			<div class="block" id="block-tables">
				<!--  div class="secondary-navigation">
				  <ul class="wat-cf">
				    <li class="first"><a href="javascript:void(0);">Ativos</a></li>
				    <li class="active"><a href="javascript:void(0);">Inativos</a></li>
				  </ul>
				</div -->
			    <div class="content">
			    	<h2 class="title">Listagem de Médicos</h2>
			    	
			    	<helper:message messageKey="${message}" />
			    	
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
									<td>
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
										<form action="<c:url value="/medicos/${doctor.id}" />" method="post">
											<input type="hidden" name="_method" value="DELETE" />
											<a href="<c:url value="/medicos/${doctor.id}/editar" />">editar</a> 
											| <a href="<c:url value="/medicos/${doctor.id}" />">exibir</a> 
											<c:if test="${doctor.active.active}">
											| <a href="#" onclick="if(confirm('Você tem certeza que deseja inativar esse médico?')) { $(this).closest('form').submit(); }">inativar</a>
											</c:if>
										</form>
									</td>
								</tr>
							</c:forEach>
						</table>
						
			            <helper:pagging total="${doctors.totalPages}" current="${doctors.currentPage}" />
					</div>
				</div>
			</div>
			
		</div>
		<!-- FIM MAIN -->
			
		<!-- INÍCIO SIDEBAR -->
		<div id="sidebar">
			
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/medicos/novo","Criar novo médico"));
			%>
			<helper:navigation links="${links}"></helper:navigation>
		
			<helper:notice notice="Titulo" title="Loren ipson dolar demet"></helper:notice>	        
	    </div>
		<!-- FIM SIDEBAR -->
	</body>
</html>