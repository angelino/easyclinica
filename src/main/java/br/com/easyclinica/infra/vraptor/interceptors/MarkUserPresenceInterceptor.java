package br.com.easyclinica.infra.vraptor.interceptors;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.easyclinica.actions.LogOffController;
import br.com.easyclinica.infra.multitenancy.LoggedUsersByTenant;
import br.com.easyclinica.infra.multitenancy.Tenant;

@RequestScoped
@Intercepts
public class MarkUserPresenceInterceptor implements Interceptor{

	private final LoggedUsersByTenant loggedUsers;
	private final Tenant tenant;

	public MarkUserPresenceInterceptor(Tenant tenant, LoggedUsersByTenant loggedUsers) {
		this.tenant = tenant;
		this.loggedUsers = loggedUsers;
		
	}
	
	public boolean accepts(ResourceMethod method) {
		return tenant.getLoggedUser().isLogged() && !method.getResource().getClass().equals(LogOffController.class);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {
		
		loggedUsers.passedBy(tenant.getLoggedUser().getEmployee().getLogin());
		stack.next(method, instance);
	}

}
