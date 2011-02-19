package br.com.easyclinica.domain.auth;

import br.com.easyclinica.actions.ReportsController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

// TODO: inverter essa condicao aqui, igual o outro filtro!
public class ReportsOnlyToFinancial implements Authorizer {

	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(ReportsController.class)
				&& employee.getPosition() != Position.FINANCIAL)
			return false;

		return true;
	}

}
