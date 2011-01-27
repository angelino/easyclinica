package br.com.easyclinica.infra.vraptor.interceptors;

import java.util.Arrays;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.actions.LoginController;
import br.com.easyclinica.infra.multitenancy.Tenant;

@Intercepts
@RequestScoped
public class LoginInterceptor implements Interceptor {

	private final Result result;
	private final Tenant tenant;

	public LoginInterceptor(Result result, Tenant tenant) {
		this.result = result;
		this.tenant = tenant;
	}
	
	@SuppressWarnings("unchecked")
	public boolean accepts(ResourceMethod resource) {
		return !Arrays.asList(LoginController.class).contains(resource.getMethod().getDeclaringClass());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if(tenant.isLogged()) {
			stack.next(method, resourceInstance);
		}
		else {
			result.redirectTo(LoginController.class).login();
		}
	}

}
