<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Agenda :.</title>
	</head>
	<body>

		<div class="box" id="agenda">
			<div class="boxcontent">			
				<h2>Agenda</h2>
				
				<form action="" method="post">
				
					<helper:message successKey="${successKey}" errorKey="${errorKey}" />
					
					<p class="required"><span>*</span> campos obrigatórios</p>
					
					<fieldset>
						<div class="medical">
							<label class="title">Médico:<span>*</span></label>
							<select name="schedule.doctor.id" min="1" data-message="Selecione um médico." <c:if test="${loggedDoctor.id > 0}">disabled="disabled"</c:if> >
								<option value="0">Selecione um médico</option>
								<c:forEach var="doctor" items="${doctors}" varStatus="status">
									<c:choose>
										<c:when test="${loggedDoctor.id == doctor.id}">
											<option value="${doctor.id}" selected="selected">${doctor.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${doctor.id}">${doctor.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</fieldset>	
					<fieldset>
						<div class="date">
		                	<label class="title">Data:<span>*</span></label>
		                    <input type="text" name="schedule.startTime" required="required" class="datepicker" />
		                </div>
					</fieldset>
					
					<div class="boxactions">
						<input type="button" id="btnCarregarCompromissos" class="btnsave" value="Salvar" />
			        </div>			
				</form>
			</div>
			
			<div class="boxcontent" id="horarios">
				
			</div>
			
		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>
