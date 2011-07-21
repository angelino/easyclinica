package br.com.easyclinica.domain.auth;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.Employee;

@Component
@RequestScoped
public class AuthSequence {

	private final List<Authorizer> authorizers;

	public AuthSequence(List<Authorizer> authorizers) {
		this.authorizers = authorizers;
	}

	public boolean checkPermissionsFor(Class<?> clazz, Employee employee) {
		for(Authorizer authorizer : authorizers) {
			if(!authorizer.allows(clazz, employee)) return false;
		}
		
		return true;
	}

}
