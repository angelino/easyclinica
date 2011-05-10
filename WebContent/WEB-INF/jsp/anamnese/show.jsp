<%@page import="br.com.easyclinica.domain.entities.Anamnese"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="br.com.easyclinica.domain.entities.Patient"%>
<%@page import="br.com.easyclinica.domain.entities.Position"%>
<%@page import="br.com.easyclinica.infra.multitenancy.LoggedUser"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica - Anamnese :.</title>
	</head>
	<body>

		<div class="box" id="anamneses" main-page="pacientes">
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			
		   		<h2>Anamnese em <fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" /></h2>				

      	<div class="date">
			<label class="title">Data:</label>
			<fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" />
		</div>
      	<div>
          	<label class="title">Médico:</label>
          	${anamnese.doctor.name}
        </div>
	      	
      	<div class="remarks">
          	<label class="title">Anamnese:</label>
            ${anamnese.text}
        </div>
  
		
			</div>			
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/anamneses/" + ((Anamnese)request.getAttribute("anamnese")).getId() + "/editar","Editar anamnese"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/anamneses/novo","Criar nova anamnese"));
				links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/anamneses","Voltar para anamneses"));
				
				LoggedUser loggedUser = (LoggedUser)request.getSession().getAttribute("loggedUser");
				if(loggedUser.getEmployee().getPosition() != Position.DOCTOR){
					links.add(new Link("/pacientes/" + ((Patient)request.getAttribute("patient")).getId() + "/editar","Editar paciente"));	
				}
				
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>
			<helper:patientDetails patient="${patient}" />
		</div>
	
	</body>
</html>