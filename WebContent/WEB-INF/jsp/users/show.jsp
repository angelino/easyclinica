<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Employee"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Exibição do Usuário :.</title>
	</head>
	<body>

		<div class="box" id="usuarios">			
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
				
  				<h2>Usuário: ${employee.name}</h2>
	
				<fieldset>
					<div>
						<label class="title">Login:</label>
						${employee.login}
					</div>
					<div>
						<label class="title">Celular:</label>
						${employee.cellphone}
					</div>					
					<div>
						<label class="title">Cargo:</label>
						<fmt:message key="${employee.position.localeKey}"></fmt:message>
					</div>					
					<div class="remarks">
			       		<label class="title">Observações:</label>
			        	${employee.observations}
			      	</div>
				</fieldset>
	
			</div>
		</div>
		
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/usuarios","Voltar para listagem"));
				links.add(new Link("/usuarios/novo","Adicionar novo usuário"));
				links.add(new Link("/usuarios/" + ((Employee)request.getAttribute("employee")).getId() + "/editar","Alterar este usuário"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>			
		</div>
	</body>
</html>
	
		
