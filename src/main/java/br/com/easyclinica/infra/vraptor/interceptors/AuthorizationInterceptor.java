package br.com.easyclinica.infra.vraptor.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.actions.HomeController;
import br.com.easyclinica.domain.auth.AuthSequence;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Intercepts(after=LoginInterceptor.class)
public class AuthorizationInterceptor implements Interceptor {

	private final LoggedUser loggedUser;
	private final AuthSequence authSequence;
	private final Result result;

	public AuthorizationInterceptor(LoggedUser loggedUser, AuthSequence authSequence, Result result) {
		this.loggedUser = loggedUser;
		this.authSequence = authSequence;
		this.result = result;
	}
	
	public boolean accepts(ResourceMethod method) {
		return loggedUser.isLogged() && !method.getMethod().getName().equals("notAuthorized");
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {
		
		if(authSequence.checkPermissionsFor(method.getMethod().getDeclaringClass(), loggedUser.getEmployee())) {
			stack.next(method, instance);
		}
		else {
			result.redirectTo(HomeController.class).notAuthorized();
		}
	}

}
