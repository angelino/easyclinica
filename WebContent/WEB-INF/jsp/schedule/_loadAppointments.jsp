<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<table border="0" class="easy compromissos">
	<tr class="tableheader">
		<th>Paciente</th>
		<th width="195">Chegada</th>
		<th width="100">Atendido?</th>
		<th width="100">&nbsp;</th>
	</tr>
</table>
	
<c:forEach items="${timeTable}" var="row" varStatus="st">

	<div class="schedule">
		<div class="time">
			<span id="${st.count}"><fmt:formatDate type="time" pattern="HH:mm" value="${row.time.time}"/></span>			
			<a class="btnaddevent" href="#">&nbsp;</a>
			<input class="addcompromisso" type="text" name="schedule.subject" time_ref="${st.count}" allowEnter="true" autocomplete="true"/>
			<span class="infocompromisso">Digite uma breve descrição do Compromisso.</span>		
		</div>
		
		<c:if test="${fn:length(row.commitments) > 0}">
			<table border="0" class="easy compromissos">

				<c:forEach items="${row.commitments}" var="commitment" varStatus="st">
					<tr schedule_id="${commitment.id}">
						<td>${commitment.subject}</td>
						<td width="195">
							<input type="text" name="arrivalTime-${commitment.id}" class="time" value="<fmt:formatDate type="time" pattern="HH:mm" value="${commitment.arrivalTime.time}"/>" />
							<a href="#" schedule_id="${commitment.id}" class="btnpeopleedit changeArrivalTime" title="Alterar horário de chegada?">&nbsp;</a>
						</td>
						<td width="100">
							<c:choose>
								<c:when test="${commitment.treated}">
									<input class="checkbox" type="checkbox" name="treated" schedule_id="${commitment.id}" checked="checked"/>
								</c:when>
								<c:otherwise>
									<input class="checkbox" type="checkbox" name="treated" schedule_id="${commitment.id}"/>
								</c:otherwise>
							</c:choose>							
						</td>
						<td width="100">
							<a class="btndelete last submit" title="Deletar compromisso" schedule_id="${commitment.id}" href="#">Excluir</a>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</c:if>
		
	</div>

</c:forEach>