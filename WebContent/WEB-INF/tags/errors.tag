<%@tag description="shows errors" pageEncoding="UTF-8"%>
<%@tag display-name="errors"%>
<%@ attribute name="errors" type="java.util.List" required="true" rtexprvalue="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty errors}"> 
	<ul class="errors">
		<c:forEach var="error" items="${errors}">
			<li><fmt:message key="${error.message}"/></li>
		</c:forEach>		
	</ul>
</c:if>