<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
	<head>
		<title>.: EasyClinica :.</title>
	</head>
	<body>
		<div class="box" id="reports">		
			<div class="boxcontent">				

				<div id="accordion">
				    <h3><a href="#">Financeiro</a></h3>
				    <div>
				    	<form action="<c:url value="/relatorios/financeiro" />" method="get">
				    	
				    		<fieldset>
				    			<div>
				    				<label class="title">Data de Início:<span>*</span></label>
				    				<input type="text" name="start" class="datepicker" required="required" />
				    			</div>
				    			
				    			<div>
				    				<label class="title">Data de Término:<span>*</span></label>
				    				<input type="text" name="end" class="datepicker" required="required"/>
				    			</div>
				    			
				    			<div>
				    				<label class="title">Médico:<span>*</span></label>
				    				<select name="doctorId" id="doctorId">
										<option value="0">Todos</option>
										<c:forEach items="${doctors}" var="doctor">
											<option value="${doctor.id}">${doctor.name}</option>
										</c:forEach>
									</select>
				    			</div>
				    			
				    			<div>
				    				<label class="title">Convênio:<span>*</span></label>
				    				<select name="planId" id="planId">
										<option value="0">Todos</option>
										<c:forEach items="${plans}" var="plan">
											<option value="${plan.id}">${plan.name}</option>
										</c:forEach>
									</select>
				    			</div>
				    		</fieldset>
				    	
				    		<div class="boxactions">	
								<input class="btnreport" type="submit" value="Gerar Relatório" />
							</div>
						</form>
					</div>
					
					<h3><a href="#">Resumo por Médico</a></h3>
				    <div>
				    	<form action="<c:url value="/relatorios/financeiro/medicos" />" method="get">
							<fieldset>
								<div>
				    				<label class="title">Data de Início:<span>*</span></label>
				    				<input type="text" name="start" class="datepicker" required="required" />
				    			</div>
				    			
				    			<div>
				    				<label class="title">Data de Término:<span>*</span></label>
				    				<input type="text" name="end" class="datepicker" required="required" />
				    			</div>
				    		</fieldset>
				    		
							<div class="boxactions">	
								<input class="btnreport" type="submit" value="Gerar Relatório" />
							</div>
						</form>
					</div>

					<h3><a href="#">Resumo por Convênios</a></h3>
				    <div>
				    	<form action="<c:url value="/relatorios/financeiro/convenios" />" method="get">
							<fieldset>
								<div>
				    				<label class="title">Data de Início:<span>*</span></label>
				    				<input type="text" name="start" class="datepicker" required="required" />
				    			</div>
				    			
				    			<div>
				    				<label class="title">Data de Término:<span>*</span></label>
				    				<input type="text" name="end" class="datepicker" required="required" />
				    			</div>
				    		</fieldset>
				    		
							<div class="boxactions">	
								<input class="btnreport" type="submit" value="Gerar Relatório" />
							</div>
						</form>
					</div>
					
					<h3><a href="#">Recibos Emitidos</a></h3>
				    <div>
				    	<form action="<c:url value="/relatorios/recibos" />" method="get">
							<fieldset>
								<div>
				    				<label class="title">Data de Início:<span>*</span></label>
				    				<input type="text" name="start" class="datepicker" required="required" />
				    			</div>
				    			
				    			<div>
				    				<label class="title">Data de Término:<span>*</span></label>
				    				<input type="text" name="end" class="datepicker" required="required" />
				    			</div>
				    		</fieldset>
				    		
							<div class="boxactions">	
								<input class="btnreport" type="submit" value="Gerar Relatório" />
							</div>
						</form>
					</div>						

				</div>

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