package br.com.easyclinica.infra.vraptor.interceptors;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.InterceptorSequence;

@Intercepts
public class EasyClinicaInterceptorsSequence implements InterceptorSequence{

	@SuppressWarnings("unchecked")
	public Class<? extends Interceptor>[] getSequence() {
		return new Class[] { 
				LoginInterceptor.class, 
				HibernateTransactionInterceptor.class };
	}

}
