package br.com.easyclinica.infra.dao.reports;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.reports.ReportGenerator;

@Component
public class ReportsHibernate implements ReportGenerator {

	private final Session session;

	public ReportsHibernate(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<Appointment> financial() {
		return (List<Appointment>) session.createCriteria(Appointment.class)
				.add(Restrictions.between("appointmentDate", startDate(), endDate()))
				.addOrder(byDate())
				.createCriteria("patient")
				.addOrder(byName()).list();
	}

	private Order byName() {
		return Order.asc("name");
	}

	private Order byDate() {
		return Order.asc("appointmentDate");
	}

	private Calendar endDate() {
		Calendar c = Calendar.getInstance();
		c.set(2011, 1, 31, 23, 59, 59);
		return c;
	}

	private Calendar startDate() {
		Calendar c = Calendar.getInstance();
		c.set(2011, 1, 1);
		return c;
	}

}
