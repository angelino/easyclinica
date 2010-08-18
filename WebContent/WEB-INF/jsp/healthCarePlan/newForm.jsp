<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<html>
	<head>
		<title>.: EasyClinica - Novo Convênio :.</title>
	</head>
	<body>

		<!-- INÍCIO MAIN -->
		<div id="main">

			<div class="block" id="block">
				
				<div class="content">
			   		<h2 class="title">Novo Convênio</h2>
					<div class="inner">
			
						<c:url value="/convenios" var="formAction" />
						
						<jsp:include page="_form.jsp">	
						   <jsp:param name="formAction" value="${formAction}" />
						</jsp:include>
					</div>
				</div>
			</div>
			
		</div>
		<!-- FIM MAIN -->
			
		<!-- INÍCIO SIDEBAR -->
		<div id="sidebar">
		
		</div>
		<!-- FIM SIDEBAR -->
	</body>
</html>