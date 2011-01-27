<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<c:url value="/pacientes/123/consultas/novo" var="x" />
	<form action="${x}" method="post">
	
		<!-- dados basicos -->
		Id convenio <input type="text" name="appointment.healthCarePlan.id" /><br/>
		id doutor <input type="text" name="appointment.doctor.id" /><br/>
		Id especialidade <input type="text" name="appointment.specialty.id" /><br/>
		retorno? <input type="text" name="appointment.isReturn" /><br/>
		data da consulta <input type="text" name="appointment.appointmentDate" /><br/>
		data de insercao <input type="text" name="appointment.date" /><br/>
		obs <input type="text" name="appointment.observations" /><br/>
		
		<!-- procedimentos -->
		<br/>
		procedimento 1
		<br/>
		id do procedimento <input type="text" name="appointment.procedures[0].procedure.id" /><br/>
		valor do procedimento <input type="text" name="appointment.procedures[0].procedure.amount" /><br/>
		eh fixo? <input type="text" name="appointment.procedures[0].procedure.isFixedAmount" /><br/>
		
		material 1<br/> 
		id <input type="text" name="appointment.procedures[0].materials[0].material.id" /><br/>
		valor unitario <input type="text" name="appointment.procedures[0].materials[0].unitAmount" /><br/>
		qty <input type="text" name="appointment.procedures[0].materials[0].qty" /><br/>
		valor total <input type="text" name="appointment.procedures[0].materials[0].totalAmount" /><br/>
		
		material 2<br/> 
		id <input type="text" name="appointment.procedures[0].materials[1].material.id" /><br/>
		valor unitario <input type="text" name="appointment.procedures[0].materials[1].unitAmount" /><br/>
		qty <input type="text" name="appointment.procedures[0].materials[1].qty" /><br/>
		valor total <input type="text" name="appointment.procedures[0].materials[1].totalAmount" /><br/>		 


		remedio 1<br/> 
		id <input type="text" name="appointment.procedures[0].medicines[0].medicine.id" /><br/>
		valor unitario <input type="text" name="appointment.procedures[0].medicines[0].unitAmount" /><br/>
		qty <input type="text" name="appointment.procedures[0].medicines[0].qty" /><br/>
		valor total <input type="text" name="appointment.procedures[0].medicines[0].totalAmount" /><br/>
		
		remedio 2<br/> 
		id <input type="text" name="appointment.procedures[0].medicines[1].medicine.id" /><br/>
		valor unitario <input type="text" name="appointment.procedures[0].medicines[1].unitAmount" /><br/>
		qty <input type="text" name="appointment.procedures[0].medicines[1].qty" /><br/>
		valor total <input type="text" name="appointment.procedures[0].medicines[1].totalAmount" /><br/>		 
		
		
		<input type="submit" />
	</form>

</body>
</html>