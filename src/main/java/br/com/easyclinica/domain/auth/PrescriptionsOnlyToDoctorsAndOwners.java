package br.com.easyclinica.domain.auth;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.actions.PrescriptionsController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

@Component
@ApplicationScoped
public class PrescriptionsOnlyToDoctorsAndOwners implements Authorizer {

	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(PrescriptionsController.class)
				&& userIsNotDoctorOrOwner(employee))
			return false;

		return true;
	}

	private boolean userIsNotDoctorOrOwner(Employee employee) {
		return (employee.getPosition() != Position.DOCTOR && employee.getPosition() != Position.OWNER);
	}

}
