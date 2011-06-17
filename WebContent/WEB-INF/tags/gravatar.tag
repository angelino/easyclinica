<%@tag import="br.com.easyclinica.infra.gravatar.GravatarImage"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="email" required="true" type="java.lang.String" rtexprvalue="true" %>
<%@ attribute name="size" required="true" type="java.lang.Integer" rtexprvalue="true" %>
<%@ attribute name="cssClass" required="true" type="java.lang.String" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:choose>
	<c:when test="${not empty email}">
		<%
			GravatarImage gravatar = new GravatarImage();
			String url = gravatar.getUrl(email, size);
		%>
		<img src="<%= url %>" class="${cssClass}"/>
	</c:when>
	<c:otherwise>
		<img src="<c:url value="/images/person.gif" />" class="${cssClass}" width="${size}" height="${size}" />
	</c:otherwise>
</c:choose>