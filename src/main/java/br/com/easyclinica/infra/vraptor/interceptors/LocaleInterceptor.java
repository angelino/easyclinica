package br.com.easyclinica.infra.vraptor.interceptors;

import java.util.Locale;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ResourceLookupInterceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

@RequestScoped
@Intercepts(before=ResourceLookupInterceptor.class, after={})
public class LocaleInterceptor implements Interceptor {

	public boolean accepts(ResourceMethod method) {
		return true;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object instance) throws InterceptionException {
		Locale.setDefault(new Locale("pt", "BR"));
		stack.next(method, instance);
	}

}
