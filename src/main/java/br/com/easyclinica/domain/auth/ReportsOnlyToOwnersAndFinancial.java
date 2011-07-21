package br.com.easyclinica.domain.auth;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.actions.ReportsController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

@Component
@ApplicationScoped
public class ReportsOnlyToOwnersAndFinancial implements Authorizer {

	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(ReportsController.class)
				&& userIsNotFinancialOrOwner(employee))
			return false;

		return true;
	}

	private boolean userIsNotFinancialOrOwner(Employee employee) {
		return (employee.getPosition() != Position.FINANCIAL && employee.getPosition() != Position.OWNER);
	}

}
