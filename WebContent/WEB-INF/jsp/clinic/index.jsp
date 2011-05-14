<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>
<html>
	<head>
		<title>.: EasyClinica - Dados da Clínica :.</title>
	</head>
	<body>

		<div class="box" id="clinica">
			<div class="boxcontent">
				
				<c:url value="/clinica" var="formAction" />
				<helper:message successKey="${successKey}" errorKey="${errorKey}" />

				<form action="${formAction}" method="post">
					<input type="hidden" name="clinic.id" value="${clinic.id}"/>
					<input type="hidden" name="clinic.domain" value="${clinic.domain}"/>
					<input type="hidden" name="clinic.active" value="${clinic.active}"/>
					<input type="hidden" name="clinic.privatePlan.id" value="${clinic.privatePlan.id}"/>
					<helper:errors errors="${errors}" />
				
					<p class="required"><span>*</span> campos obrigatórios</p>
				
					<fieldset>
						<div>
							<label class="title">Name:<span>*</span></label>
							<input type="text" name="clinic.name" maxlength="50" tabindex="1" required="required" value="${clinic.name}" />
						</div>
						
						<div class="telephone">
							<label class="title">Telefone:<span>*</span></label>
				    		<input type="text" name="clinic.telephone" tabindex="3" class="mask_telefone" required="required" maxlength="50" value="${clinic.telephone}" />
				    	</div>
				
						<div class="title">
							<label class="title">Fax:</label>
				    		<input type="text" name="clinic.fax" tabindex="5" class="mask_telefone" maxlength="50" value="${clinic.fax}" />
				    	</div>
				    	
				    	<div class="title">
							<label class="title">E-mail de cobrança:</label>
				    		<input type="text" name="clinic.billingEmail" tabindex="7" maxlength="150" value="${clinic.billingEmail}" />
				    	</div>
				    	
				    	<div>
							<label class="title">Endereço:</label>
					    	<input type="text" name="clinic.address.street" tabindex="9" maxlength="150" value="${clinic.address.street}" />
					    </div>
				    	
				    	<div>
							<label class="title">Cidade:</label>
							<input type="text" name="clinic.address.city" tabindex="11" maxlength="50" value="${clinic.address.city}" />
						</div>
				    	
					</fieldset>
					
					<fieldset>	
						<div class="title">
							<label class="title">CNPJ:<span>*</span></label>
							<input type="text" name="clinic.cnpj" class="mask_cnpj" tabindex="2" required="required" maxlength="12" value="${clinic.cnpj}" />
						</div>
						
						<div class="telephone">
							<label class="title">Telefone:</label>
				    		<input type="text" name="clinic.telephone2" tabindex="4" class="mask_telefone" maxlength="50" value="${clinic.telephone2}" />
				    	</div>
						
						<div class="title">
							<label class="title">E-mail de contato:</label>
				    		<input type="text" name="clinic.contactEmail" tabindex="6" required="required" maxlength="150" value="${clinic.contactEmail}" />
				    	</div>
						
						<div>
							<label class="title">Website:</label>
							<input type="text" name="clinic.website" tabindex="8" maxlength="150" value="${clinic.website}" />
						</div>
						
						<div>
							<label class="title">Bairro:</label>
							<input type="text" name="clinic.address.neighborhood" tabindex="10" maxlength="100" value="${clinic.address.neighborhood}" />
						</div>
						
						<div>
							<label class="title">Estado:</label>
					    	<input type="text" name="clinic.address.state" tabindex="12" maxlength="2" value="${clinic.address.state}" />
					    </div>
					
				    </fieldset>
				    
				    <div class="boxactions">
						<input type="submit" class="btnsave" value="Salvar" />
				        <input type="button" class="btncancel" value="Cancelar" redirect_to="<c:url value="/"/>"/>
				    </div>
				  
				</form>
			</div>
		</div>
			
		<div class="boxright">
			<% 
				java.util.List<Link> links = new LinkedList<Link>();  
				links.add(new Link("/","Voltar para mural"));
				pageContext.setAttribute("links",links);
			%>
			<helper:navigation links="${links}"></helper:navigation>		
		</div>
	</body>
</html>