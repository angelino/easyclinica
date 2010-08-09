<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="block" id="block">
	
	<div class="content">
   		<h2 class="title">Novo Convênio</h2>
		<div class="inner">

<form action="../" method="post">
	
	<!--  erros -->

	<fieldset class="cadastro">	
		<label class="label">Nome*:</label>
		<input type="text" name="healthCarePlan.name.name" class="text_field" />
    	<span class="description">Ex: 'Amil', 'Blue Life'</span>
	
		<label class="label">Endereço:</label>
    	<input type="text" name="healthCarePlan.address.street.street" class="text_field" />
    	<span class="description">Ex: 'Av. Paulista, 123'</span>
		
		<div class="agrupar_campos">
			<label class="label">Bairro:</label>
	    <input type="text" name="healthCarePlan.address.neighborhood.neighborhood" class="text_field" />
	    <span class="description">Ex: 'Jardins'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">CEP:</label>
	    <input type="text" name="healthCarePlan.address.postalCode.postalCode" class="text_field" />
	    <span class="description">Ex: '12345-789'</span>		
		</div>
	
		<label class="label">Nome do Responsável:</label>
    <input type="text" name="healthCarePlan.contact.name" class="text_field" />
    <span class="description">Ex: 'José da Silva'</span>
	
		<div class="agrupar_campos">
			<label class="label">Telefone*:</label>
    	<input type="text" name="healthCarePlan.telephone.telephone" class="text_field" />
    	<span class="description">Ex: '(11) 1111-1111'</span>
		</div>
		
		<div class="agrupar_campos">
			<label class="label">E-mail:</label>
    	<input type="text" name="healthCarePlan.email.email" class="text_field" />
    	<span class="description">Ex: 'pessoa@convenio.com.br'</span>
		</div>
		
		<label class="label">Website:</label>
    <input type="text" name="healthCarePlan.website.website" class="text_field" />
    <span class="description">Ex: 'http://www.convenio.com.br'</span>
	
	<div class="agrupar_campos">
		<label class="label">Tabela de Serviços*:</label>
	
	<span class="description">Ex: 'AMB99'</span>
	</div>
	
	<div class="agrupar_campos">
		<label class="label">Valor em R$ da CH:</label>
	
	<span class="description">Ex: 'R$ 0.15'</span>
	</div>
	
		<label class="label">Observações</label>
    <input type="text" name="healthCarePlan.observations.observations" class="text_field" />
    <span class="description">Ex: 'só atende ortopedia'</span>
	
		<div class="botoes">
			<button class="button" type="submit"><img src="images/tick.png" alt="Salver" />Salvar</button>
			<img src="images/cross.png" alt="Cancelar" /> Cancelar
		</div>
	
	</fieldset>
  
</form>

		</div>
	</div>
</div>