package br.com.easyclinica.domain.auth;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.actions.AppointmentsController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

@Component
@ApplicationScoped
public class AppointmentsNotToDoctors implements Authorizer {

	public boolean allows(Class<?> clazz, Employee employee) {

		if (clazz.equals(AppointmentsController.class)
				&& employee.getPosition() == Position.DOCTOR)
			return false;

		return true;
	}

}
