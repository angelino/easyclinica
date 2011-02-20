<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/easyclinica.tld" prefix="helper" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><decorator:title default="Easy Clinica" /></title>

		<helper:include fileName="style.css" type="css" />
		<helper:include fileName="plugins/jquery-ui/jquery-ui-1.8.2.custom.css" type="css" />
		<helper:include fileName="plugins/jquery.autocomplete/jquery.autocomplete.css" type="css" />
		
		<helper:include fileName="plugins/jquery-1.4.4.min.js" type="js" />
		<helper:include fileName="plugins/jquery.tools/jquery.tools.min.js" type="js" />
		<helper:include fileName="plugins/jquery-ui/jquery-ui-1.8.4.custom.min.js" type="js" />
	    <helper:include fileName="plugins/jquery.maskedinput/jquery.maskedinput-1.2.2.min.js" type="js" />	    
	    <helper:include fileName="plugins/jquery.autocomplete/jquery.autocomplete.js" type="js" />
	    <helper:include fileName="easyclinica.js" type="js" />
	    <helper:include fileName="consultas.js" type="js" />
	    <helper:include fileName="medicos.js" type="js" />
	    <helper:include fileName="convenios.js" type="js" />
	    <helper:include fileName="pacientes.js" type="js" />
	    <helper:include fileName="anamnese.js" type="js" />
	    <helper:include fileName="agenda.js" type="js" />
	    
	    <decorator:head />
	</head>

    <body>
    
    	<div class="main">
            <!-- START HEADER -->
            <div class="header">
       	    <h1><a href="#">Easy Clínica</a></h1>

                <ul class="menulogin">
                  <li><a href="#">Dados da Clínica</a></li>
                  <li><a href="#">Configurações</a></li>
                  <li><a href="#" class="logout">Logout</a></li>
                </ul>

                <ul class="menu" id="menu-principal">
                  <li class="first" id="menu-link-dashboard"><a href="<c:url value="/dashboard" />">Dashboard</a></li>
                  <li id="menu-link-pacientes"><a href="<c:url value="/pacientes" />">Pacientes</a></li>
                  <li id="menu-link-convenios"><a href="<c:url value="/convenios" />">Convênios</a></li>
                  <li id="menu-link-medicos"><a href="<c:url value="/medicos" />">Médicos</a></li>
                </ul>
            </div>
            <!-- END HEADER -->
        	
	    	<!-- START CONTENT -->
            <div class="content">
    			<decorator:body />    			
			</div>
            <!-- END CONTENT -->
            
            <!-- START FOOTER -->
            <!--div class="footer">
            	<p>Copyright &copy; 2011 Easy Clínica.</p>
            </div-->
            <!-- END FOOTER -->
            
        </div>
				
    </body>
</html>