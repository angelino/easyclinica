<%@tag description="shows message" pageEncoding="UTF-8"%>
<%@tag display-name="message"%>
<%@ attribute name="successKey" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ attribute name="errorKey" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty successKey}">
	<div class="flash">
		<div class="message notice">
			<fmt:message key="${successKey}" />
		</div>
	</div>
</c:if>

<c:if test="${not empty errorKey}">
	<div class="flash">
		<div class="message error">
			<fmt:message key="${errorKey}" />
		</div>
	</div>
</c:if>