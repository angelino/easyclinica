<%@tag import="java.util.Calendar"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="list" type="java.util.List" rtexprvalue="true" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<c:choose>
 		<c:when test="${fn:length(list) == 0}">
 			<p class="messengernotice">
 				Nenhuma mensagem foi postada ainda! Seja o primeiro!
 			</p>
  		</c:when>
		<c:otherwise>
		
			<c:forEach var="message" items="${list}">
		
				<div class="boxfb">
		          <helper:gravatar email="${message.employee.email}" size="50" cssClass="photobig"/>
		          <span class="date"><fmt:formatDate value="${message.date.time}" pattern="dd/MM/yyyy HH:mm" /></span>
		          <p><a href="javascript:void(0);">${message.employee.name}</a>: ${message.text}</p>
		          
					
				  <c:forEach var="reply" items="${message.replies}">
						<div class="boxpost">
							<helper:gravatar email="${reply.employee.email}" size="30" cssClass="photo"></helper:gravatar>
							<span class="date"><fmt:formatDate value="${reply.date.time}" pattern="dd/MM/yyyy HH:mm" /></span>
							<p><a href="javascript:void(0);">${reply.employee.name}</a>: ${reply.text}</p>
						</div>
				  </c:forEach>
						
				  <div class="boxpost loading">
						<helper:gravatar email="${loggedUser.employee.email}" size="30" cssClass="photo"></helper:gravatar>
						<form method="post" class="form_publica_comentario">
							<fieldset>
								<textarea name="reply"></textarea>
								<input type="hidden" name="message" value="${message.id}" />
								<input type="button" class="btn reply-button" value="Comentar" />
							</fieldset>
						</form>
						<span class="loading">&nbsp;</span>
              	  </div>
				</div>
			</c:forEach>
		</c:otherwise>
</c:choose>