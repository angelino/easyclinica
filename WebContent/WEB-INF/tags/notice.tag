<%@tag description="Menu lateral para apresentação de notícias" pageEncoding="UTF-8"%>
<%@tag display-name="notice"%>

<%@ attribute name="title" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ attribute name="notice" type="java.lang.String" required="true" rtexprvalue="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="block notice">
  <h4><c:out value="${title}"/></h4>
  <p><c:out value="${notice}"/></p>
</div>