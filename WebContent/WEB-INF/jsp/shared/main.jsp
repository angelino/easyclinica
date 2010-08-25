<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><decorator:title default="Bem vindo" /></title>
	    
		<helper:include fileName="reset.css" type="css" />
		<helper:include fileName="base.css" type="css" />
		<helper:include fileName="style.css" type="css" />
		<helper:include fileName="jquery-ui-1.8.2.custom.css" type="css" />
    
	    <helper:include fileName="jquery-1.4.2.min.js" type="js" />
	    <helper:include fileName="jquery-ui-1.8.2.custom.min.js" type="js" />
	    <helper:include fileName="jquery.maskedinput-1.2.2.min.js" type="js" />
	    <helper:include fileName="easyclinica.js" type="js" />
	    
	    <decorator:head />
	</head>

    <body>
		<div id="container">
		    <div id="header">
		      <h1><a href="index.html">.: Easy Clínica :.</a></h1>
		      <div id="user-navigation">
				<ul class="wat-cf">
				  <li><a href="#">Dados da Clínica</a></li>
				  <li><a href="#">Configurações</a></li>
				  <li><a class="logout" href="#">Logout</a></li>
				</ul>
		      </div>
		      
		      <div id="main-navigation">	
		        <ul class="wat-cf" id="menu-principal">
		          <li class="first" id="menu-link-dashboard"><a href="javascript:void(0);">Dashboard</a></li>
		          <li id="menu-link-pacientes">
		          	<c:url value="/pacientes" var="pacientes"></c:url>
		          	<a href="${pacientes}">Pacientes</a>
		          </li>
		          <li id="menu-link-convenios">
		          	<c:url value="/convenios" var="convenios"></c:url>
		          	<a href="${convenios}">Convênios</a>
		          </li>
		          <li id="menu-link-medicos">
		          	<c:url value="/medicos" var="medicos"></c:url>
		          	<a href="${medicos}">Médicos</a>
		          </li>
		        </ul>
		      </div>
		    </div>
	    	
	    	<div id="wrapper" class="wat-cf">
    			<decorator:body />    			
			</div>
			
			<div id="footer">
				Copyright &copy; 2010 EasyClinica.
		    </div>
			
		</div>
	
    </body>
</html>