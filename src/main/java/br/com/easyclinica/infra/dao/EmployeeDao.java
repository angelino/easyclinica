package br.com.easyclinica.infra.dao;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<Employee> get() {
		return session.createCriteria(Employee.class).list();
	}

	public void add(Employee employee) {
		session.save(employee);
	}

	public Employee getById(int id) {
		return (Employee) session.load(Employee.class, id);
	}

	public void update(Employee employee) {
		session.merge(employee);
	}

}
