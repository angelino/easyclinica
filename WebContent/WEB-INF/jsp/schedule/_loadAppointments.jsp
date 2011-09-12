<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<table border="0" class="easy compromissos">
	<tr class="tableheader">
		<th class="name">Paciente</th>
		<th width="140">Chegada</th>
		<th width="70">Atendido?</th>
		<th width="35">&nbsp;</th>
	</tr>
</table>
	
<c:forEach items="${timeTable}" var="row" varStatus="st">

	<div class="schedule">
		<div class="time">
			<span id="${st.count}"><fmt:formatDate type="time" pattern="HH:mm" value="${row.time.time}"/></span>			
			<a class="btnaddevent" id="add_event_${st.count}" href="#">&nbsp;</a>
			<input class="addcompromisso" type="text" id="schedule_subject_${st.count}" name="schedule.subject" time_ref="${st.count}" allowEnter="true" autocomplete="true"/>
			<span class="infocompromisso">Digite uma breve descrição do Compromisso.</span>		
		</div>
		
		<c:if test="${fn:length(row.commitments) > 0}">
			<table border="0" class="easy compromissos">

				<c:forEach items="${row.commitments}" var="commitment" varStatus="itemSt">
					<tr schedule_id="${commitment.id}">
						<td class="name">${commitment.subject}</td>
						<td width="140">
							<input type="text" name="arrivalTime-${commitment.id}" class="time" value="<fmt:formatDate type="time" pattern="HH:mm" value="${commitment.arrivalTime.time}"/>" id="arrivalTime_${st.count}_${itemSt.count}"/>
							<a href="#" schedule_id="${commitment.id}" class="btnpeopleedit changeArrivalTime last" title="Alterar horário de chegada?" id="save_arrivalTime_${st.count}_${itemSt.count}">&nbsp;</a>
						</td>
						<td width="70">
							<c:choose>
								<c:when test="${commitment.treated}">
									<input class="checkbox" type="checkbox" name="treated" schedule_id="${commitment.id}" id="treated_${st.count}_${itemSt.count}" checked="checked"/>
								</c:when>
								<c:otherwise>
									<input class="checkbox" type="checkbox" name="treated" schedule_id="${commitment.id}" id="treated_${st.count}_${itemSt.count}" />
								</c:otherwise>
							</c:choose>							
						</td>
						<td width="35">
							<a class="btndelete last submit" title="Deletar compromisso" schedule_id="${commitment.id}" href="#" id="delete_${st.count}_${itemSt.count}">Excluir</a>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</c:if>
		
	</div>

</c:forEach>