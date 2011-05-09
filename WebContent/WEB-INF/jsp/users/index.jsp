<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Listagem de Usuários :.</title>
	</head>
	<body>

		<div class="box" id="usuarios">
			<div class="boxcontent">
				<h2>Listagem de Usuários</h2>
			    	
			    <helper:message successKey="${successKey}" errorKey="${errorKey}" />
			 			    	
		    	<c:choose>
		    		<c:when test="${fn:length(employees) == 0}">
		    			<p class="messengernotice">
		    				Não há usuários cadastrados! <a href="<c:url value="/usuarios/novo" />">Clique aqui</a> para adicionar o primeiro!
		    			</p>
		    		</c:when>
		    		<c:otherwise>   	
			       		<table border="0" class="easy">
							<tr class="tableheader">
								<th width="65px">Status</th>
								<th>Nome</th>
								<th>Telefone</th>
								<th>Login</th>
								<th>Cargo</th>
								<th width="171px">&nbsp;</th>
							</tr>
							
							<c:forEach var="employee" items="${employees}" varStatus="status">
								<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
									
									<c:choose>
										<c:when test="${employee.active}">
											<td class="statusenable">Ativo</td>
                                    		<td>${employee.name}</td>
										</c:when>
										<c:otherwise>
											<td class="statusdisable">Inativo</td>
                                    		<td class="namedisable">${employee.name}</td>
										</c:otherwise>
									</c:choose>
									
									<td>${employee.cellphone}</td>
									<td>${employee.login}</td>
									<td><fmt:message key="${employee.position.localeKey}"></fmt:message></td>
									<td class="buttons">
										<a class="btnpeopleedit" title="Editar" href="<c:url value="/usuarios/${employee.id}/editar" />">&nbsp;</a>
                                        <a class="btnpeopleshow" title="Exibir" href="<c:url value="/usuarios/${employee.id}" />">&nbsp;</a>
                                        
                                        <c:if test="${loggedUser.employee.id != employee.id}">
	                                        <c:choose>
												<c:when test="${employee.active}">
													<form action="<c:url value="/usuarios/${employee.id}/desativar" />" method="post">
			                                        	<input type="hidden" name="_method" value="PUT" />
			                                        	<a class="btnpeopledisable last submit" title="Desativar" href="#">&nbsp;</a>
													</form>
												</c:when>
												<c:otherwise>
													<form action="<c:url value="/usuarios/${employee.id}/ativar" />" method="post">
			                                        	<input type="hidden" name="_method" value="PUT" />
			                                        	<a class="btnpeopleenable last submit" title="Ativar" href="#">&nbsp;</a>
													</form>
												</c:otherwise>
											</c:choose>
										</c:if>
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
				links.add(new Link("/usuarios/novo","Criar novo usuário"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>

		</div>
	</body>
</html>