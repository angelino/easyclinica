<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>

		<div id="main">
		
			<c:url value="/login" var="loginUrl" />
			<form action="${loginUrl}" method="post">
				<input type="text" name="login" />
				<input type="text" name="password" />
				
				<input type="submit" />
			</form>
	
		</div>
			
		<div id="sidebar">

		</div>
	</body>
</html>