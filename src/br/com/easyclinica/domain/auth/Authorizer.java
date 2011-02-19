package br.com.easyclinica.domain.auth;

import br.com.easyclinica.domain.entities.Employee;

public interface Authorizer {
	boolean allows(Class<?> clazz, Employee employee);
}
