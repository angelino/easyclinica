package br.com.easyclinica.domain.auth;

import br.com.easyclinica.actions.ScheduleController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

public class ScheduleOnlyToOwnersFinancialsAndAttendants implements Authorizer {
	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(ScheduleController.class)
				&& !isOwner(employee) && !isAttendant(employee) && !isFinancial(employee))
			return false;

		return true;
	}

	private boolean isAttendant(Employee employee) {
		return employee.getPosition() == Position.ATTENDANT;
	}

	private boolean isFinancial(Employee employee) {
		return employee.getPosition() == Position.FINANCIAL;
	}

	
	private boolean isOwner(Employee employee) {
		return employee.getPosition() == Position.OWNER;
	}
}
