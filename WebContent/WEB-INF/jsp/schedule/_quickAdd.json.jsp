<%@ page language="java" contentType="application/json" pageEncoding="utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object>
	<json:property name="Data" value="${schedule.id}"/>
	<json:property name="IsSuccess" value="${true}"/>
	<json:property name="Msg" value="Succefully"/>
</json:object>