package br.com.easyclinica.domain.auth;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.actions.ReceiptController;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.Position;

@Component
@ApplicationScoped
public class ReceiptOnlyToOwnersAndFinancial implements Authorizer {

	public boolean allows(Class<?> clazz, Employee employee) {
		if (clazz.equals(ReceiptController.class)
				&& userIsNotFinancialOrOwner(employee))
			return false;

		return true;
	}

	private boolean userIsNotFinancialOrOwner(Employee employee) {
		return (employee.getPosition() != Position.FINANCIAL && employee.getPosition() != Position.OWNER);
	}

}
