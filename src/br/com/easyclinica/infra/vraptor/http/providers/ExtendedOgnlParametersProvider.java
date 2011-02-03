package br.com.easyclinica.infra.vraptor.http.providers;

import javax.servlet.http.HttpServletRequest;

import ognl.DefaultMemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import br.com.caelum.vraptor.core.Converters;
import br.com.caelum.vraptor.http.ParameterNameProvider;
import br.com.caelum.vraptor.http.ognl.EmptyElementsRemoval;
import br.com.caelum.vraptor.http.ognl.OgnlParametersProvider;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class ExtendedOgnlParametersProvider extends OgnlParametersProvider {

	public ExtendedOgnlParametersProvider(Converters converters,
			ParameterNameProvider provider, HttpServletRequest request,
			EmptyElementsRemoval removal) {
		super(converters, provider, request, removal);
	}
	
	@Override
	protected OgnlContext createOgnlContext(Object root) {
		OgnlContext ctx = (OgnlContext) Ognl.createDefaultContext(root);
		Ognl.setMemberAccess(ctx, new DefaultMemberAccess(true));
		
		return ctx;
	}

}
