<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>.: EasyClinica - Nova Consulta :.</title>
</head>
<body>
	Data: <input type="text" name="appointmentDate">
	Médico: 
	<select name="doctor">
	
	</select>
	
	Particular <input type="radio" name="healthCarePlan" value="123" />
	ou do Paciente: <input type="radio" name="healthCarePlan" value="123" />
	
	Retorno? Sim <input type="radio" name="isReturn" value="true" />
	Não <input type="radio" name="isReturn" value="false" />
	
	Especialidade:
	
	<select name="specialty">
	
	</select>
	
	<input type="hidden" name="patient" value="123" />
	
	Procedimento: <input type="text" name="procedure" />
	
	<table>
		<tr>
			<td>Código</td>
			<td>Procedimento</td>
			<td>Preço Unitário</td>
			<td>Preço Total</td>
			<td>Ações</td>
		</tr>
		
	</table>
	
	Observações:
	<textarea name="observations">
	</textarea>
	
</body>
</html>