package br.com.easyclinica.infra.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.repositories.AllEmployees;

@Component
public class EmployeeDao implements AllEmployees {

	private final Session session;
	public EmployeeDao(Session session) {
		this.session = session;
	}
	
	public Employee getByLogin(String login) {
		return (Employee) session.createCriteria(Employee.class).add(Restrictions.eq("login", login)).uniqueResult();
	}

}
