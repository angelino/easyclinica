<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="procedure">
	<h3>${procedure.name}</h3>
	<input type="hidden" name="appointment.procedures[#index#].id" value="${procedure.id}"/>
</div>