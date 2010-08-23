<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
<head>
	<title>.: EasyClinica - Agenda :.</title>
	
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#calendario").datepicker();
		});
	</script>
</head>
<body>
	<!-- INÍCIO MAIN -->
   	<div id="main">
   		<div id="block-tables" class="block">
   			
   			<div class="content">
   				<h2 class="title">Agenda</h2>
   				
   				<div class="inner agenda">
   					
   					<div class="topo">
   						<div class="hoje">
   							<div class="mes">
   								AGO/2010
   							</div>
   							<div class="dia">
   								23
   							</div>
   						</div>
   						
   						<div class="filtros">
   							<fieldset class="cadastro">
   								<label>Escolha um médico:</label>
   								<input type="text" name="txtMedico" id="txtMedico"/>
   								
   								<button class="button" type="submit">
									<c:url value="/images/icons/search.png" var="img_buscar"/>
									<img src="${img_buscar}" alt="Buscar" />Buscar
								</button>
   							</fieldset>
   						</div>
   					</div>
   					
   					<div class="compromissos">
   						<h3>Lista de compromissos</h3>
   						
   						<table class="table">
							<tr>
								<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_compromissos"/></th>
								<th>Horário</th>
								<th>Paciente</th>
								<th>Telefone</th>
								<th class="last">&nbsp;</th>
							</tr>
							
							<tr class="odd">
								<td><input type="checkbox" class="checkbox" rel="chk_compromissos" name="id" value="" /></td>
								<td>9:00 - 10:00</td>
								<td>Nome do Paciente</td>
								<td>11 3361 7001</td>
								<td>
									<a href="">editar</a> 
								</td>
							</tr>
							<tr class="even">
								<td><input type="checkbox" class="checkbox" rel="chk_compromissos" name="id" value="" /></td>
								<td>9:00 - 10:00</td>
								<td>Nome do Paciente</td>
								<td>11 3361 7001</td>
								<td>
									<a href="">editar</a> 
								</td>
							</tr>
						</table>
   					</div>
   					
   					<div class="calendario">
   						<div id="calendario"></div>
   					</div>
   					
   				</div>
   			</div>
   			
   		</div>   	
   	</div>
   	<!-- FIM MAIN -->
   	
   	<!-- INÍCIO SIDEBAR -->
	<div id="sidebar">
	</div>
	<!-- FIM SIDEBAR -->
</body>
</html>