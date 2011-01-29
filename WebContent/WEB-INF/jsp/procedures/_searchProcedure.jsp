<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
	<c:when test="${fn:length(procedures) == 0}">
		<p>Nenhum procedimento foi encontrado!</p>
	</c:when>
	<c:otherwise>
		<table class="table">
			<tr>
				<th class="first"></th>
				<th>AMB</th>
				<th>TUSS</th>
				<th>Nome</th>
				<th>CH</th>
				<th class="last">&nbsp;</th>
			</tr>
			
			<c:forEach var="procedure" items="${procedures}" varStatus="status">
				<tr class="${status.count % 2 == 0 ? 'odd' : 'even' }">
					<td></td>
					<td>${procedure.ambCode}</td>
					<td>${procedure.tussCode}</td>
					<td>${procedure.name}</td>
					<td>${procedure.ch}</td>
					<td>
						<a href="" procedure_id="${procedure.id}" class="add-procedure">adicionar</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>