package br.com.easyclinica.infra.dao.reports;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.reports.FinancialReportGenerator;

@Component
public class FinancialReportHibernate implements FinancialReportGenerator {

	private final Session session;

	public FinancialReportHibernate(Session session) {
		this.session = session;
	}


	@SuppressWarnings("unchecked")
	public List<Appointment> financial(Calendar startDate, Calendar endDate,
			Doctor doctor, HealthCarePlan plan) {
		Criteria report = session.createCriteria(Appointment.class)
		.add(Restrictions.between("appointmentDate", startDate, endDate));
		
		if(doctor!=null) report.add(Restrictions.eq("doctor", doctor));
		if(plan!=null) report.add(Restrictions.eq("healthCarePlan", plan));
		
		report		
		.addOrder(byDate())
		.createCriteria("patient")
		.addOrder(byName());
		
		return report.list();
	}
	
	private Order byName() {
		return Order.asc("name");
	}

	private Order byDate() {
		return Order.asc("appointmentDate");
	}


}
