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
			<helper:patientMenu patient="${patient}" selected="Anamnese" />
			
			<div class="boxcontent">
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />
			
		   		<h2>Anamnese em <fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" /></h2>				
				
				<fieldset>
			      	<div class="date">
						<label class="title">Data:</label>
						<fmt:formatDate value="${anamnese.date.time}" pattern="dd/MM/yyyy" />
					</div>
			      	<div>
			          	<label class="title">Médico:</label>
			          	${anamnese.doctor.name}
			        </div>
				      	
			      	<div>
			          	<label class="title">Queixa e Duração:</label>
			            ${anamnese.complaintAndDuration}
			        </div>
			
			      	<div>
			          	<label class="title">HPMA:</label>
			            ${anamnese.hpma}
			        </div>
			        
			      	<div>
			          	<label class="title">HSDA:</label>
			            ${anamnese.hsda}
			        </div>
			        
			      	<div>
			          	<label class="title">HF:</label>
			            ${anamnese.hf}
			        </div>
			        
			      	<div>
			          	<label class="title">Exame Clínico:</label>
			            ${anamnese.clinicExam}
			        </div>
			        
			      	<div>
			          	<label class="title">Exame Suplementar:</label>
			            ${anamnese.supplementaryExam}
			        </div>
			        
			      	<div>
			          	<label class="title">Hipótese Diagnóstica:</label>
			            ${anamnese.hypothesis}
			        </div>
			        
			      	<div>
			          	<label class="title">CID:</label>
			            ${anamnese.cid.name}
			        </div>
			        
			      	<div>
			          	<label class="title">Conduta:</label>
			            ${anamnese.conduct}
			        </div>  
				</fieldset>
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