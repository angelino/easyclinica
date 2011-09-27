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
				
				<c:if test="${newbie.newbie}">
				<h2>Seja bem vindo ao Easy Clínica!</h2>
				<div style="padding-bottom:15px;">
				Olá, seja bem vindo ao Easy Clínica, o sistema de gerenciamento que
				facilita a vida do médico.
				
				Você ainda não tem nenhum paciente cadastrado! Se quiser ajuda para começar,
				assista o vídeo abaixo!
				</div>
				
				<iframe src="http://player.vimeo.com/video/28370112?title=0&amp;byline=0&amp;portrait=0&amp;color=62a0ac" frameborder="0" width="670" height="400"></iframe>
				</c:if>

				<h2>Mural</h2>
								
				<div class="newpost">
					<span>Compartilhe informações com toda a clínica!</span>
					<form action="">
						<fieldset>							
							<textarea id="messageText" class="comment"></textarea>
							<input type="button" value="Postar" class="btn message-button" />
						</fieldset>
					</form>	
				</div>
				
				<div id="allMessages">
					<helper:messages list="${messages}" />
				</div>
								
			</div>
		</div>
		
		<div class="boxright">			
			<helper:schedule list="${schedules}"></helper:schedule>   
	    </div>
	</body>
</html>