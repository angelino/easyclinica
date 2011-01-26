package br.com.easyclinica.infra.multitenancy;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DefaultTenantUrlParser implements TenantUrlParser {

	public String parse(String url) {
		return url.replace("http://", "").replace("www.", "").split("\\.")[0];
	}
}
