<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${timeTable}" var="row" varStatus="st">

	<div class="schedule">
		<span><fmt:formatDate type="time" pattern="HH:mm" value="${row.time.time}"/></span>
	</div>

</c:forEach>