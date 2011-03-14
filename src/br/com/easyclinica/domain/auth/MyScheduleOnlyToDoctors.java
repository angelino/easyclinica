package br.com.easyclinica.domain.auth;

import br.com.easyclinica.actions.MyScheduleController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

public class MyScheduleOnlyToDoctors implements Authorizer {
	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(MyScheduleController.class)
				&& employee.getPosition() != Position.DOCTOR)
			return false;

		return true;
	}
}
