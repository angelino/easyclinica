<%@tag description="shows message" pageEncoding="UTF-8"%>
<%@tag display-name="message"%>
<%@ attribute name="messageKey" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="message error">
	<c:if test="${not empty messageKey}">
		<fmt:message key="${messageKey}" />
	</c:if>
</div>