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
		
				<div class="box_post">
		          <div class="box_img">
		              <helper:gravatar email="${message.employee.email}" size="50"></helper:gravatar>
		          </div>
		          <p><strong>${message.employee.name}</strong>${message.text}</p>
		
					<p class="tempo"><span><fmt:formatDate value="${message.date.time}" pattern="dd/MM/yyyy HH:mm" /></span></p>
					
					<div class="box_comments_post">
						<ul class="comments">
							<c:forEach var="reply" items="${message.replies}">
								<li>
									<div class="img_foto_post"><helper:gravatar email="${reply.employee.email}" size="30"></helper:gravatar></div>
									<div class="post"><p class="txt_post">${reply.text}</p></div>
								</li>
							</c:forEach>
						</ul>
						
						<ul class="actions">
							<li>
								<form method="post" class="form_publica_comentario">
									<div class="img_foto_post">
										<helper:gravatar email="${loggedUser.employee.email}" size="30"></helper:gravatar>
									</div>
									<textarea class="comment" name="reply"></textarea>
									<input type="hidden" name="message" value="${message.id}" />
									<input type="button" class="reply-button" value="Comentar" />
								</form>
							</li>
						</ul>
					</div>
					
				</div>
			</c:forEach>
		</c:otherwise>
</c:choose>