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
				
				<div>
					O que você está fazendo agora?
					<form action="<c:url value="/mensagens" />" method="post">
						<input type="text" name="message.text"/>
						<input type="submit" value="Postar" />
					</form>
				</div>
				
				<c:forEach var="message" items="${messages}">
					<div>
						<helper:gravatar email="${message.employee.email}" size="50"></helper:gravatar>
						${message.text}
					</div>
					<c:forEach var="reply" items="${message.replies}">
						<div>
							<helper:gravatar email="${reply.employee.email}" size="50"></helper:gravatar>
							${reply.text}
						</div>
					</c:forEach>
					<div>
					Responder:
					<form action="<c:url value="/mensagens/${message.id}/respostas" />" method="post">
						<input type="text" name="reply" />
						<input type="submit" />
					</form>
					</div>
				</c:forEach>
				
								
			</div>
		</div>
	</body>
</html>