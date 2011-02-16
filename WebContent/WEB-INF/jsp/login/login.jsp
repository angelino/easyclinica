<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Easy Clínica</title>
	    
		<helper:include fileName="style.css" type="css" />
	</head>
	<body>
	
		<div class="main">
		    <div class="boxlogin">
				<h1>Easy Clínica</h1>
			    <h2>Login:</h2>
			    <p class="messengererror">Error message</p>
			    
			    <form action="<c:url value="/login" />" class="form login" method="post"> 
			    	<fieldset>
			        	<label>Nome:</label>
			            <input type="text" />
			        </fieldset>
			    	<fieldset>
			        	<label>Senha:</label>
			            <input type="text" />
			        </fieldset>
			        <fieldset class="boxactions">
			            <input type="submit" class="btnlogin" value="Login" />
			        </fieldset>
			    </form>
			</div>
		</div>
	
	</body>
</html>