<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>.: Easy Clínica :.</title>
	
	<!-- CSS -->
	<link rel="stylesheet" href="stylesheets/reset.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="stylesheets/base.css" type="text/css" media="screen" />
 	<link rel="stylesheet" href="stylesheets/style.css" type="text/css" media="screen" />
  	<link rel="stylesheet" href="stylesheets/jquery-ui-1.8.2.custom.css" type="text/css" media="screen" />
  
  	<!-- JAVASCRIPTS -->
  	<script type="text/javascript" charset="utf-8" src="javascripts/jquery-1.4.2.min.js"></script>
  	<script type="text/javascript" charset="utf-8" src="javascripts/jquery-ui-1.8.2.custom.min.js"></script>
  	<script type="text/javascript" charset="utf-8" src="javascripts/jquery.maskedinput-1.2.2.min.js"></script>
  	<script type="text/javascript" charset="utf-8" src="javascripts/easyclinica.js"></script>
</head>
<body>
	<!-- INÍCIO CONTAINER -->
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
	          <li id="menu-link-pacientes"><a href="javascript:void(0);">Pacientes</a></li>
	          <li id="menu-link-convenios"><a href="convenios">Convênios</a></li>
	          <li id="menu-link-medicos"><a href="javascript:void(0);">Médicos</a></li>
	        </ul>
	      </div>
	    </div>
    	
    	<!-- INÍCIO WRAPPER -->
    	<div id="wrapper" class="wat-cf">
    		
    		<!-- INÍCIO MAIN -->
      		<div id="main">