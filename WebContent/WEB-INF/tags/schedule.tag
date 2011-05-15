<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="list" type="java.util.List" rtexprvalue="true" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="schedule-widget">
	<h4>
		Agenda (próx. 2 horas)
		<a href="<c:url value="/agenda" />" class="btnschedule" title="Ver agenda">&nbsp;</a>
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