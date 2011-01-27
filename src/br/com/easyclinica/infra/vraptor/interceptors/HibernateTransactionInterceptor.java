package br.com.easyclinica.infra.vraptor.interceptors;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class HibernateTransactionInterceptor implements Interceptor {

	private final Session session;

	public HibernateTransactionInterceptor(Session session) {
		this.session = session;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			stack.next(method, instance);
			transaction.commit();
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public boolean accepts(ResourceMethod method) {
		return true; // Will intercept all requests
	}
}
