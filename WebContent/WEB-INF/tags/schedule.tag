<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="list" type="java.util.List" rtexprvalue="true" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="schedule-widget">
	<h4>Agenda (próx. 2 horas)
		<c:choose>
			<c:when test="${loggedUser.doctor}">
				<c:url value="/medicos/minha-agenda" var="schedule_link"/>
			</c:when>
			<c:otherwise>
				<c:url value="/agenda" var="schedule_link"/>
			</c:otherwise>
		</c:choose> 
		<a href="${schedule_link}" class="btnschedule" title="Ver agenda completa">&nbsp;</a>
	</h4>
	
	<c:choose>
		<c:when test="${fn:length(list) == 0}">
			<p>Nenhum compromisso para as próximas 2 horas</p>
		</c:when>
		<c:otherwise>
			<ul>
				<c:forEach items="${list}" var="schedule">
					<li class="hour"><fmt:formatDate value="${schedule.startTime.time}" pattern="HH:mm" /></li>
					<li class="subject">Dr(a) ${schedule.doctor.name} - ${schedule.subject}</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
</div>