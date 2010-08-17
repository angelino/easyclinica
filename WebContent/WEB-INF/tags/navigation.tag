<%@tag description="Menu lateral com links de navegação" pageEncoding="UTF-8"%>
<%@tag display-name="navigation"%>

<%@ attribute name="links" type="java.util.List" required="true" rtexprvalue="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="block">
	<h3>Navegação</h3>
	<ul class="navigation">
		<c:forEach items="${links}" var="link">
			<c:url value="${link.Href}" var="url"/>
		
		    <li><a href="${url}" target="${link.Target}" class="links">${link.Description}</a></li>
		</c:forEach>
	</ul>
</div>