package br.com.easyclinica.infra.vraptor.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.actions.HomeController;
import br.com.easyclinica.domain.auth.AuthSequenceBuilder;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Intercepts(after=LoginInterceptor.class)
public class AuthorizationInterceptor implements Interceptor {

	private final LoggedUser loggedUser;
	private final AuthSequenceBuilder authBuilder;
	private final Result result;

	public AuthorizationInterceptor(LoggedUser loggedUser, AuthSequenceBuilder authBuilder, Result result) {
		this.loggedUser = loggedUser;
		this.authBuilder = authBuilder;
		this.result = result;
	}
	
	public boolean accepts(ResourceMethod method) {
		return loggedUser.isLogged() && !method.getMethod().getName().equals("notAuthorized");
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {
		
		if(authBuilder.build().checkPermissionsFor(method.getMethod().getDeclaringClass(), loggedUser.getEmployee())) {
			stack.next(method, instance);
		}
		else {
			result.redirectTo(HomeController.class).notAuthorized();
		}
	}

}
