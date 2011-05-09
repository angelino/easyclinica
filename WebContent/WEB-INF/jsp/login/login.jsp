<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Easy Clínica</title>
	    
		<helper:include fileName="style.css" type="css" />
		<helper:include fileName="plugins/jquery-1.4.4.min.js" type="js" />
		<script type="text/javascript">
			$(function() {
			  $("#login").focus();
			});
		</script>
	</head>
	<body>
	
		<div class="main">
		    <div class="boxlogin">
				<h1>Easy Clínica</h1>
			    <h2>Login:</h2>
			    <c:if test="${errorLogin == true}">
			    <p class="messengererror">Login e/ou senha incorretos.</p>
			    </c:if>
			    
			    <form action="<c:url value="/login" />" class="form login" method="post"> 
			    	<fieldset>
			        	<label>Login:</label>
			            <input type="text" name="login" id="login" />
			        </fieldset>
			    	<fieldset>
			        	<label>Senha:</label>
			            <input type="password" name="password" id="password"/>
			        </fieldset>
			        <fieldset class="boxactions">
			            <input type="submit" class="btnlogin" value="Login" id="btnlogin" />
			        </fieldset>
			    </form>
			</div>
		</div>
	
	</body>
</html>