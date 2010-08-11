<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<div class="block" id="block">
	
	<div class="content">
   		<h2 class="title">Editar Convênio</h2>
		<div class="inner">

			<c:url value="/convenios/${healthCarePlan.id}" var="formAction" />
			<helper:healthCarePlanForm action="${formAction}" errors="${errors}" model="${healthCarePlan}" />
		</div>
	</div>
</div>