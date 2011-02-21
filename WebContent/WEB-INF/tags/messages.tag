<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="list" type="java.util.List" rtexprvalue="true" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<c:forEach var="message" items="${list}">
	<div><helper:gravatar email="${message.employee.email}" size="50"></helper:gravatar>
	${message.text}</div>
	<c:forEach var="reply" items="${message.replies}">
		<div><helper:gravatar email="${reply.employee.email}" size="30"></helper:gravatar>
		${reply.text}</div>
	</c:forEach>
	<div>Responder:
	<form method="post">
		<input type="text" name="reply" /> 
		<input type="hidden" name="message" value="${message.id}" />
		<input type="button" class="reply-button" value="Responder" />
	</form>
	</div>
</c:forEach>