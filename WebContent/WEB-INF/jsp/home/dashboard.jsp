<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box" id="dashboard">		
			<div class="boxcontent">
				<h2>Dashboard</h2>
				
				<div class="newPost">
					O que você está fazendo agora?
						<textarea id="messageText" class="comment"></textarea>
						<input type="button" value="Postar" class="message-button" />
				</div>
				
				<div id="allMessages">
					<helper:messages list="${messages}" />
				</div>
								
			</div>
		</div>
	</body>
</html>