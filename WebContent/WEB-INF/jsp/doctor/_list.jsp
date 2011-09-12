<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

   <c:choose>
  		<c:when test="${fn:length(doctors.result) == 0}">
  			<p class="messengernotice">
  				Não há médicos cadastrados! <a href='<c:url value="/medicos/novo" />'>Clique aqui</a> para adicionar o primeiro!
  			</p>
  		</c:when>
  		<c:otherwise>
       	<table border="0" class="easy">
			<tr class="tableheader">
				<th width="19px"></th>
				<th>Nome</th>
				<th>CRM</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th width="42px">&nbsp;</th>
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
					<td>${doctor.crm} (${doctor.specialty.name})</td>
					<td>${doctor.telephone}</td>
					<td>${doctor.email}</td>
					<td class="buttons">
						<c:if test="${!loggedUser.doctor || loggedUser.employee.doctor.id == doctor.id}">
							<span class="btntools">Ferramentas
               					<span class="boxtools">
               						<span class="buttons">
                						<span class="arrow">&nbsp;</span>
							
											<a class="btnpeopleedit" title="Editar" href="<c:url value="/medicos/${doctor.id}/editar" />">&nbsp;</a>
				                                     <a class="btnpeopleshow" title="Exibir" href="<c:url value="/medicos/${doctor.id}" />">&nbsp;</a>
				                                     
				                                     <c:choose>
				                                     	<c:when test="${loggedUser.doctor}">
				                                     		<c:url value="/medicos/minha-agenda" var="agenda_url"/>
				                                     	</c:when>
				                                     	<c:otherwise>
				                                     		<c:url value="/agenda" var="agenda_url"/>
				                                     	</c:otherwise>
				                                     </c:choose>
				                                     <a class="btnschedule" title="Agenda" href="${agenda_url}">&nbsp;</a>
				                                     
				                                     <c:choose>
												<c:when test="${doctor.active}">
													<form action="<c:url value="/medicos/${doctor.id}/deactivate" />" method="post">
				                                       	<input type="hidden" name="_method" value="PUT" />
				                                       	<a class="btnpeopledisable last submit" title="Desativar" href="#">&nbsp;</a>
													</form>
												</c:when>
												<c:otherwise>
													<form action="<c:url value="/medicos/${doctor.id}/activate" />" method="post">
				                                       	<input type="hidden" name="_method" value="PUT" />
				                                       	<a class="btnpeopleenable last submit" title="Ativar" href="#">&nbsp;</a>
													</form>
												</c:otherwise>
											</c:choose>
										</span>
									</span>
								</span>
						</c:if>
                    </td>
				</tr>
			</c:forEach>
		</table>
		
           <helper:pagging total="${doctors.totalPages}" current="${doctors.currentPage}" />
	
	</c:otherwise>
</c:choose>