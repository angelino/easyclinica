<%@tag description="tag responsável pela inclusão de arquivos css e javascript" pageEncoding="UTF-8"%>
<%@tag display-name="include"%>
<%@ attribute name="fileName" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ attribute name="type" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${type == 'css'}">
<c:url value="/stylesheets/${fileName}" var="css"></c:url>
<link rel="stylesheet" href="${css}" type="text/css" media="screen" />
</c:if>
<c:if test="${type == 'js'}">
<c:url value="/javascripts/${fileName}" var="js"></c:url>
<script type="text/javascript" language="javascript" src="${js}" charset="utf-8"></script>
</c:if>