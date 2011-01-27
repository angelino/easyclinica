package br.com.easyclinica.infra.vraptor.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.infra.multitenancy.Tenant;
import br.com.easyclinica.infra.multitenancy.TenantUrlParser;

// TODO: rodar antes do login
@Intercepts
@RequestScoped
public class MultiTenancyInterceptor implements Interceptor {

	private final Tenant tenant;
	private final TenantUrlParser urlParser;

	public MultiTenancyInterceptor(Tenant tenant, TenantUrlParser urlParser) {
		this.tenant = tenant;
		this.urlParser = urlParser;
	}
	
	public boolean accepts(ResourceMethod arg0) {
		return !tenant.isLogged();
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		tenant.setTempDomain(urlParser.parse());
		stack.next(method, resourceInstance);
	}

}
