package br.com.easyclinica.domain.auth;

import br.com.easyclinica.domain.entities.Employee;

public class AuthSequence {

	private final Authorizer[] authorizers;

	public AuthSequence(Authorizer... authorizers) {
		this.authorizers = authorizers;
	}

	public boolean checkPermissionsFor(Class<?> clazz, Employee employee) {
		for(Authorizer authorizer : authorizers) {
			if(!authorizer.allows(clazz, employee)) return false;
		}
		
		return true;
	}

}
