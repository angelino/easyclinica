package br.com.easyclinica.domain.auth;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.actions.ClinicController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

@Component
@ApplicationScoped
public class ClinicInfoOnlyToOwners implements Authorizer {
	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(ClinicController.class)
				&& employee.getPosition() != Position.OWNER)
			return false;

		return true;
	}
}
