package br.com.easyclinica.infra.multitenancy;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.infra.web.HttpRequestWrapper;

@Component
@RequestScoped
public class DefaultTenantUrlParser implements TenantUrlParser {

	private final HttpRequestWrapper request;

	public DefaultTenantUrlParser(HttpRequestWrapper request) {
		this.request = request;
	}
	
	public String parse() {
		return url().replace("http://", "").replace("www.", "").split("\\.")[0];
	}

	private String url() {
		return request.getUrl();
	}
}
