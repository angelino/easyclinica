<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
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
				    	<form action="<c:url value="/relatorios/financeiro" />" method="get" id="form">
						Data de Início: <input type="text" name="start" id="start" class="datepicker2" /> <br />
						Data de Fim: <input type="text" name="end" id="end" class="datepicker2" /> <br />
						Médico:
							<select name="doctorId" id="doctorId">
								<option value="0">Todos</option>
								<c:forEach items="${doctors}" var="doctor">
									<option value="${doctor.id}">${doctor.name}</option>
								</c:forEach>
							</select>
						
						Convênio:
						
							<select name="planId" id="planId">
								<option value="0">Todos</option>
								<c:forEach items="${plans}" var="plan">
									<option value="${plan.id}">${plan.name}</option>
								</c:forEach>
							</select>
							
							<input type="submit" value="Gerar Relatório" id="generateFinancialReport" />
						</form>
					</div>
					
					 <h3><a href="#">Financeiro / Médicos</a></h3>
				    <div>
				    	<form action="<c:url value="/relatorios/financeiro/medicos" />" method="get" id="form">
							Data de Início: <input type="text" name="start" class="datepicker" /> <br />
							Data de Fim: <input type="text" name="end" class="datepicker" /> <br />
							<input type="submit" value="Gerar Relatório" id="generateFinancialReport" />
						</form>
					</div>
					
				    <h3><a href="#">Próximo</a></h3>
				    <div>Second content</div>
				</div>

			</div>
		</div>
	</body>
</html>