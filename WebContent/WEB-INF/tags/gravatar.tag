<%@tag import="br.com.easyclinica.infra.gravatar.GravatarImage"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="email" required="true" type="java.lang.String" rtexprvalue="true" %>
<%@ attribute name="size" required="true" type="java.lang.Integer" rtexprvalue="true" %>
<%
	GravatarImage gravatar = new GravatarImage();
	String url = gravatar.getUrl(email, size);
%>
<img src="<%= url %>" />