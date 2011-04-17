package br.com.easyclinica.infra.dao.reports;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.reports.FinancialByDoctorReportData;
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
		c.set(2011, 0, 31, 23, 59, 59);
		return c;
	}

	private Calendar startDate() {
		Calendar c = Calendar.getInstance();
		c.set(2011, 0, 1);
		return c;
	}

	@SuppressWarnings("unchecked")
	public List<FinancialByDoctorReportData> financialByDoctor(Calendar start, Calendar end) {
		Criteria criteria = session.createCriteria(Appointment.class)
								   .add(Restrictions.between("appointmentDate", start, end))
								   .setProjection( Projections.projectionList()
										   .add(Projections.rowCount(), "qtyAppointments")
										   .add(Projections.sum("procedureAmount"), "procedureAmount")
										   .add(Projections.sum("appointmentAmount"), "appointmentAmount")
										   .add(Projections.sum("roomRateAmount"), "roomRateAmount")
										   .add(Projections.property("doctor"), "doctor")
										   .add(Projections.groupProperty("doctor"))
								   )
								   .setResultTransformer(Transformers.aliasToBean(FinancialByDoctorReportData.class))
								   .createCriteria("doctor")
								   .addOrder(Order.asc("name"));
		
		return (List<FinancialByDoctorReportData>)criteria.list();
	}
}
