package br.com.easyclinica.infra.vraptor.interceptors;

import java.util.Arrays;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.actions.LoginController;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@RequestScoped
public class LoginInterceptor implements Interceptor {

	private final Result result;
	private final LoggedUser loggedUser;

	public LoginInterceptor(Result result, LoggedUser loggedUser) {
		this.result = result;
		this.loggedUser = loggedUser;
	}
	
	@SuppressWarnings("unchecked")
	public boolean accepts(ResourceMethod resource) {
		return !Arrays.asList(LoginController.class).contains(resource.getMethod().getDeclaringClass());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		if(loggedUser.isLogged()) {
			stack.next(method, resourceInstance);
		}
		else {
			result.redirectTo(LoginController.class).login();
		}
	}

}
