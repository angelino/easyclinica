<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>


  	<c:choose>
  		<c:when test="${fn:length(healthcares.result) == 0}">
  			<p class="messengernotice">
  				Não há convênios cadastrados! <a href="<c:url value="/convenios/novo" />">Clique aqui</a> para adicionar o primeiro!
  			</p>
  		</c:when>
  		<c:otherwise>   	
      		<table border="0" class="easy">
			<tr class="tableheader">
				<th width="20px">&nbsp;</th>
				<th>Nome:</th>
				<th>Contato:</th>
				<th>Telefone:</th>
				<th>Valor CH (R$):</th>
				<th width="42px">&nbsp;</th>
			</tr>
			
			<c:forEach var="healthCare" items="${healthcares.result}" varStatus="status">
				<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
					
					<c:choose>
						<c:when test="${healthCare.active}">
							<td class="statusenable">Ativo</td>
                                		<td>${healthCare.name}</td>
						</c:when>
						<c:otherwise>
							<td class="statusdisable">Inativo</td>
                                		<td class="namedisable">${healthCare.name}</td>
						</c:otherwise>
					</c:choose>
					
					<td>${healthCare.contact}</td>
					<td>${healthCare.telephone}</td>
					<td class="currency">${healthCare.ch}</td>
					<td class="buttons">                       
	                   	<span class="btntools">
	                       	Ferramentas
	                       	<span class="boxtools">
	                       		<span class="buttons">
		                       		<span class="arrow">&nbsp;</span>
		                       		
			                        <a class="btnpeopleshow" title="Exibir" href="<c:url value="/convenios/${healthCare.id}"/>">&nbsp;</a>
									<a class="btnpeopleedit" title="Editar" href="<c:url value="/convenios/${healthCare.id}/editar" />">&nbsp;</a>
			                                    
			                        <c:if test="${healthCare.id != loggedUser.clinic.privatePlan.id}">
			                                     
			                            <c:choose>
											<c:when test="${healthCare.active}">
												<form action="<c:url value="/convenios/${healthCare.id}/deactivate" />" method="post">
			                                       	<input type="hidden" name="_method" value="PUT" />
			                                       	<a class="btnpeopledisable last submit" title="Desativar" href="#">&nbsp;</a>
												</form>
											</c:when>
											<c:otherwise>
												<form action="<c:url value="/convenios/${healthCare.id}/activate" />" method="post">
			                                       	<input type="hidden" name="_method" value="PUT" />
			                                       	<a class="btnpeopleenable last submit" title="Ativar" href="#">&nbsp;</a>
												</form>
											</c:otherwise>
										</c:choose>									
			                        </c:if>
		                        </span>
	                         </span>
	                       </span>
                    </td>
				</tr>
			</c:forEach>
		</table>
		
           <helper:pagging total="${healthcares.totalPages}" current="${healthcares.currentPage}" />
	
	</c:otherwise>
</c:choose>
		