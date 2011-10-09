<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:forEach var="patient" items="${patients}">
	${patient.name}|${patient.id}
</c:forEach>
