<%@tag description="pagging" pageEncoding="UTF-8"%>
<%@tag display-name="pagging"%>
<%@ attribute name="total" type="java.lang.Integer" required="true" rtexprvalue="true" %>
<%@ attribute name="current" type="java.lang.Integer" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="actions-bar wat-cf">

	<div class="pagination">
		
		<% if(current == 1) { %>
		<span class="disabled prev_page">« Anterior</span>
		<% } else { %>
		<a rel="next" class="prev_page" href="?page=<%= current - 1 %>">« Anterior</a>
		<% } %>
		
		<% for(int i = 1; i <= total; i++) { %>
			<% if (current == i) { %>
			<span class="current"><%= i %></span>
			<% } else { %>
			<a rel="next" href="?page=<%= i %>"><%= i %></a>
			<% } %>
		<% } %>
		
		<% if(current.equals(total)) { %>
		<span class="disabled next_page">Próxima »</span>
		<% } else { %>
		<a rel="next" class="next_page" href="?page=<%= current + 1 %>">Próxima »</a>
		<% } %>
	</div>
</div>