<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<%@page import="br.com.easyclinica.domain.entities.Position"%>
<%@page import="br.com.easyclinica.infra.multitenancy.LoggedUser"%>

<html>
	<head>
		<title>.: EasyClinica - Listagem de Pacientes :.</title>
	</head>
	<body>

		<div class="box" id="pacientes">
		
			<div class="boxcontent">
            	<h2>Listagem de Pacientes</h2>
                <fieldset class="search">
                	<div class="search">
                        <input type="text" name="patient.textobusca"/>
                        <a href="#" id="btnBuscar">Buscar</a>
                        <p>Digite parte do nome do paciente, ou email, ou CPF.</p>
                    </div>
                </fieldset>
            </div>
		
			<div class="boxcontent" id="box_listagem">
			    <helper:message successKey="${successKey}" errorKey="${errorKey}" />
			    	
		    	<jsp:include page="_list.jsp"/>
			</div>	
		</div>
			
		<div class="boxright">	
			<% 
				java.util.List<Link> links = new LinkedList<Link>();
			
				LoggedUser loggedUser = (LoggedUser)request.getSession().getAttribute("loggedUser");
				if(loggedUser.getEmployee().getPosition() != Position.DOCTOR){
					links.add(new Link("/pacientes/novo","Criar novo paciente"));
				}
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
		</div>
	</body>
</html>