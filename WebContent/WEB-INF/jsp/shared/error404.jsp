<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Easy Clínica</title>
	    
		<helper:include fileName="style.css" type="css" />
		<helper:include fileName="plugins/jquery-1.4.4.min.js" type="js" />
	</head>
	<body>
	
		<div class="main">
			<br/><br/><br/><br/>
			<p class="messengernotice">
				Página não encontrada. Por favor, tente novamente.
				<br/>
				<a href='<c:url value="/" />' class="btn">Voltar!</a>
			</p>
		</div>
	
	</body>
</html>