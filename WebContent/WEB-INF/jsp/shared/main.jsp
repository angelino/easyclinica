<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>.: Easy Clínica - <decorator:title default="Bem vindo" /> :.</title>
	    
	    <!-- CSS -->
	    <link rel="stylesheet" href="<%= request.getContextPath() %>/stylesheets/reset.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="<%= request.getContextPath() %>/stylesheets/base.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="<%= request.getContextPath() %>/stylesheets/style.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="<%= request.getContextPath() %>/stylesheets/jquery-ui-1.8.2.custom.css" type="text/css" media="screen" />
	    
	    <!-- JAVASCRIPTS -->
	    <script type="text/javascript" charset="utf-8" src="<%= request.getContextPath() %>/javascripts/jquery-1.4.2.min.js"></script>
	    <script type="text/javascript" charset="utf-8" src="<%= request.getContextPath() %>/javascripts/jquery-ui-1.8.2.custom.min.js"></script>
	    <script type="text/javascript" charset="utf-8" src="<%= request.getContextPath() %>/javascripts/jquery.maskedinput-1.2.2.min.js"></script>
	    <script type="text/javascript" charset="utf-8" src="<%= request.getContextPath() %>/javascripts/easyclinica.js"></script>
	   
	    <decorator:head />
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
		          <li id="menu-link-convenios">
		          	<c:url value="/convenios" var="convenios"></c:url>
		          	<a href="${convenios}">Convênios</a>
		          </li>
		          <li id="menu-link-medicos"><a href="javascript:void(0);">Médicos</a></li>
		        </ul>
		      </div>
		    </div>
	    	
	    	<!-- INÍCIO WRAPPER -->
	    	<div id="wrapper" class="wat-cf">
	    		
	    		<!-- INÍCIO MAIN -->
	      		<div id="main">
           			<decorator:body />
    			</div>
				<!-- FIM MAIN -->
			
				<!-- INÍCIO SIDEBAR -->
				<div id="sidebar">
					<div class="block">
			          <h3>Navegação</h3>
			          <ul class="navigation">
			            <li><a href="#">Default</a></li>
			          </ul>
			        </div>
			        
			        <div class="block notice">
			          <h4>Notice Title</h4>
			          <p>Morbi posuere urna vitae nunc. Curabitur ultrices, lorem ac aliquam blandit, lectus eros hendrerit eros, at eleifend libero ipsum hendrerit urna. Suspendisse viverra. Morbi ut magna. Praesent id ipsum. Sed feugiat ipsum ut felis. Fusce vitae nibh sed risus commodo pulvinar. Duis ut dolor. Cras ac erat pulvinar tortor porta sodales. Aenean tempor venenatis dolor.</p>
			        </div>
				</div>
				<!-- FIM SIDEBAR -->
			</div>
			<!-- FIM WRAPPER -->
		
			<!-- INÍCIO FOOTER -->
			<div id="footer">
				Copyright &copy; 2010 EasyClinica.
		    </div>
		    <!-- FIM FOOTER -->
			
		</div>
		<!-- FIM CONTAINER  -->
    
    </body>
</html>