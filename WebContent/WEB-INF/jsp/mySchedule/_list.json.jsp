<%@ page language="java" contentType="application/json" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
	<json:array name="events" var="schedule" items="${schedules}">
		<json:array>
			<json:property value="${schedule.id}"/>
			<json:property value="${schedule.subject}"/>
			<json:property>
				<fmt:formatDate value="${schedule.startTime.time}" pattern="MM/dd/yyyy HH:mm" />
			</json:property>
			<json:property>
				<fmt:formatDate value="${schedule.endTime.time}" pattern="MM/dd/yyyy HH:mm" />
			</json:property>
			<json:property value="${0}"/>  
			<json:property value="${0}"/>  
			<json:property value="${0}"/>  			
			<json:property value="${schedule.color}"/> 
			<json:property value="${1}"/>
			<json:property value="${schedule.description}"/>
			<json:property value=""/>
		</json:array>
	</json:array>
	
	<json:property name="issort" value="${true}" />
	<json:property name="start">
		<fmt:formatDate value="${start.time}" pattern="MM/dd/yyyy HH:mm" />
	</json:property>
	<json:property name="end">
		<fmt:formatDate value="${end.time}" pattern="MM/dd/yyyy HH:mm" />
	</json:property>
	<json:property name="error" value="${null}" />
</json:object>
