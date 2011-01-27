package br.com.easyclinica.infra.web;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class DefaultHttpRequestWrapper implements HttpRequestWrapper{

	private final HttpServletRequest request;
	
	public DefaultHttpRequestWrapper(HttpServletRequest request) {
		this.request = request;
	}
	public String getUrl() {
		return request.getRequestURL().toString();
	}

}
