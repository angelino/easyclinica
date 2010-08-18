<%@tag description="shows errors" pageEncoding="UTF-8"%>
<%@tag display-name="errors"%>
<%@ attribute name="errors" type="java.util.List" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty errors}"> 
	<div class="flash">
		<div class="message error">
			<c:forEach var="error" items="${errors}">
					<p><fmt:message key="${error.message}"/></p>
			</c:forEach>
		</div>
	</div>
</c:if>