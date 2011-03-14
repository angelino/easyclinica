package br.com.easyclinica.domain.auth;

import br.com.easyclinica.actions.ScheduleController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

public class ScheduleOnlyToOwnersAndAttendants implements Authorizer {
	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(ScheduleController.class)
				&& (isOwner(employee) || isAttendant(employee)))
			return true;

		return false;
	}

	private boolean isAttendant(Employee employee) {
		return employee.getPosition() == Position.ATTENDANT;
	}

	private boolean isOwner(Employee employee) {
		return employee.getPosition() == Position.OWNER;
	}
}
