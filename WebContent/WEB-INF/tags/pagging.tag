<%@tag description="pagging" pageEncoding="UTF-8"%>
<%@tag display-name="pagging"%>
<%@ attribute name="total" type="java.lang.Integer" required="true" rtexprvalue="true" %>
<%@ attribute name="current" type="java.lang.Integer" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${total > 1}">
	<div class="boxpagination">
		<a href="?page=1" page="1" class="first"><img src="<c:url value="images/icons/resultset_first.png"/>" /></a>
		<% if(current == 1) { %>
			<a href="javascript:void(0);" class="previous"><img src="<c:url value="images/icons/resultset_previous.png" />" /></a>
		<% } else { %>
			<a href="?page=<%= current - 1 %>" page="<%= current - 1 %>" class="previous"><img src="<c:url value="images/icons/resultset_previous.png" />" /></a>
		<% } %>
		
		<%
			int firstLink = current - 3 > 0 ? current - 3 : 1;
			int lastLink = current + 3 < total ? current + 3 : total; 
		%>
		<% for(int i = firstLink; i <= lastLink; i++) { %>
			<% if (current == i) { %>
			<a href="javascript:void(0);" class="active"><%= i %></a>
			<% } else { %>
			<a href="?page=<%= i %>" page="<%= i %>"><%= i %></a>
			<% } %>
		<% } %>
		
		<% if(current.equals(total)) { %>
			<a href="javascript:void(0);" class="next"><img src="<c:url value="images/icons/resultset_next.png" />" /></a>
		<% } else { %>
			<a href="?page=<%= current + 1 %>" page="<%= current + 1 %>" class="next"><img src="<c:url value="images/icons/resultset_next.png" />" /></a>
		<% } %>
		<a href="?page=<%= total %>" page="<%= total %>" class="last"><img src="<c:url value="images/icons/resultset_last.png" />" /></a>
	</div>
</c:if>
