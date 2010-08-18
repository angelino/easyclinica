<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica - Listagem de Convênios :.</title>
	</head>
	<body>	
		<div class="block" id="block-tables">
			<!--  div class="secondary-navigation">
			  <ul class="wat-cf">
			    <li class="first"><a href="javascript:void(0);">Ativos</a></li>
			    <li class="active"><a href="javascript:void(0);">Inativos</a></li>
			  </ul>
			</div -->
		    <div class="content">
		    	<h2 class="title">Listagem de Convênios</h2>
		    	
		    	<helper:message messageKey="${successKey}" errorKey="${errorKey}" />
		    	
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
								<td>
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
									<form action="<c:url value="/convenios/${healthCare.id}" />" method="post">
										<input type="hidden" name="_method" value="DELETE" />
										<a href="<c:url value="/convenios/${healthCare.id}" />">exibir</a> 
										<c:if test="${healthCare.active.active}">
										| <a href="#" onclick="if(confirm('Você tem certeza?')) { $(this).closest('form').submit(); }">inativar</a>
										</c:if>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
					
		            <helper:pagging total="${healthcares.totalPages}" current="${healthcares.currentPage}" />
				</div>
			</div>
		</div>
	</body>
</html>