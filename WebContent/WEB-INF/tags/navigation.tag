<%@tag description="Menu lateral com links de navegação" pageEncoding="UTF-8"%>
<%@tag display-name="navigation"%>

<%@ attribute name="links" type="java.util.List" required="true" rtexprvalue="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="simpleboxlink">
	<h4>Navegação</h4>
	<ul>
		<c:forEach items="${links}" var="link">
			<li><a href="<c:url value="${link.href}"/>" target="${link.target}">${link.description}</a></li>
		</c:forEach>
	</ul>
</div>