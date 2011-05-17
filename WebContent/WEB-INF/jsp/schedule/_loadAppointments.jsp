<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach items="${timeTable}" var="row" varStatus="st">

	<div class="schedule">
		<span><fmt:formatDate type="time" pattern="HH:mm" value="${row.time.time}"/></span>
		
		<c:if test="${fn:length(row.commitments) > 0}">
			<table border="0" class="easy">
				<tr class="tableheader">
					<th>Compromisso:</th>
					<th>Chegada</th>
					<th>Atendido?</th>
					<th>&nbsp;</th>
				</tr>
				
				<c:forEach items="${row.commitments}" var="commitment" varStatus="st">
					<tr>
						<td>${commitment.subject}</td>
						<td>
							<input type="text" name="arrivalTime-${commitment.id}" class="time" value="<fmt:formatDate type="time" pattern="HH:mm" value="${commitment.arrivalTime.time}"/>" />
							<a href="#" schedule_id="${commitment.id}" class="btnpeopleedit changeArrivalTime" title="Alterar horário de chegada?">&nbsp;</a>
						</td>
						<td>
							<input type="checkbox" name="treated" schedule_id="${commitment.id}" value="1"/>
						</td>
						<td>
							<a class="btndelete last submit" title="Deletar compromisso" href="#">Excluir</a>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</c:if>
		
	</div>

</c:forEach>