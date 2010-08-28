<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="br.com.easyclinica.view.Link"%>
<%@page import="java.util.LinkedList"%>

<html>
<head>
	<title>.: EasyClinica - Agenda :.</title>
	
	<helper:include fileName="jquery.simplemodal.1.4.min.js" type="js" />
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#calendario").datepicker();

			$("#data").datepicker({ altFormat: 'dd/mm/yy' });
		});

		function open_modal() {
			$("#agendamento").modal();
		}
	</script>
</head>
<body>

	<!-- MODAL AGENDAMENTO -->
	<div id="agendamento" class="modal agendamento">
		<a href="#" class="simplemodal-close btn_fechar">X</a>
	
		<h3>Médico: Fulano </h3>
		<em>CRM: 55.555</em>		
				
		<fieldset class="cadastro">
			<div class="paciente">
				<label>Paciente*:</label>
				<input type="text" name="paciente" />			
				<button class="button" type="submit">
					<c:url value="/images/icons/schedule/search.png" var="img_buscar"/>
					<img src="${img_buscar}" alt="Buscar" />
				</button>
			</div>
			
			<div class="agrupar_campos">
				<label>Data*:</label>
				<input type="text" id="data" name="data"/>
			</div>
			
			<div class="agrupar_campos">
				<label>Tipo*:</label>
				<select name="tipo">
					<option>Consulta</option>
					<option>Retorno</option>
				</select>	
			</div>
						
			<div class="agrupar_campos" style="clear:both;">
				<label>Início*:</label>
				<select name="inicio">
					<option>09:00</option>
				</select>	
			</div>
			
			<div class="agrupar_campos">
				<label>Término*:</label>
				<select name="fim">
					<option>10:00</option>
				</select>	
			</div>
						
			<label>Descrição*:</label>
			<textarea name="descricao"></textarea>
			
			<div class="botoes">
				<button class="button" type="submit">
					<c:url value="/images/icons/tick.png" var="img_salvar"/>
					<img src="${img_salvar}" alt="Salvar" />Salvar
				</button>
				
				<a class="button simplemodal-close" href="#">
					<c:url value="/images/icons/cross.png" var="img_cancelar"/>
					<img src="${img_cancelar}" alt="Cancelar" />Cancelar
				</a>
			</div>
		</fieldset>
	</div>
	<!-- FIM MODAL AGENDAMENTO -->

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
   							<fieldset class="cadastro busca">
   								<label>Escolha um médico:</label>
   								<select name="medico" id="medico">
   									
   								</select>
   								
   								<button class="button" type="submit">
									<c:url value="/images/icons/schedule/search.png" var="img_buscar"/>
									<img src="${img_buscar}" alt="Buscar" />Buscar
								</button>
   							</fieldset>
   						</div>
   					</div>
   					
   					<div class="compromissos">
   						<ul class="acoes">
   							<li>
   								<a class="button" href="javascript:open_modal()">
									<c:url value="/images/icons/schedule/add.png" var="img_add"/>
									<img src="${img_add}" alt="Novo" />Novo
								</a>
   							</li>
   						</ul>
   						
   						<table class="table">
							<tr>
								<th class="first"><input type="checkbox" class="checkbox toggle check_all" rel="chk_compromissos"/></th>
								<th>Horário</th>
								<th>Paciente</th>
								<th>Telefone</th>
								<th class="last">&nbsp;</th>
							</tr>
							
							<tr class="indisponivel">
								<td><input type="checkbox" class="checkbox" rel="chk_compromissos" name="id" value="" /></td>
								<td>9:00 - 10:00</td>
								<td>Nome do Paciente</td>
								<td>11 3361 7001</td>
								<td>
									<a href="javascript:open_modal()" title="Editar">
										<c:url value="/images/icons/schedule/alterar.png" var="img_alterar"/>
										<img src="${img_alterar}" alt="Editar" width="20" height="20" />
									</a>
									&nbsp;
									<a href="#" title="Excluir">
										<c:url value="/images/icons/schedule/deletar.png" var="img_deletar"/>
										<img src="${img_deletar}" alt="Excluir" width="20" height="20" />
									</a>
								</td>
							</tr>
							<tr class="current">
								<td><input type="checkbox" class="checkbox" rel="chk_compromissos" name="id" value="" /></td>
								<td>9:00 - 10:00</td>
								<td>Nome do Paciente</td>
								<td>11 3361 7001</td>
								<td></td>
							</tr>
							<tr class="disponivel">
								<td><input type="checkbox" class="checkbox" rel="chk_compromissos" name="id" value="" /></td>
								<td>9:00 - 10:00</td>
								<td> - </td>
								<td> - </td>
								<td></td>
							</tr>
						</table>
						
						<ul class="legenda">
   							<li><span class="vermelho"></span>Horário indisponível</li>
   							<li><span class="azul"></span>Horário atual</li>
   							<li><span class="verde"></span>Horário disponível</li>
   						</ul>
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