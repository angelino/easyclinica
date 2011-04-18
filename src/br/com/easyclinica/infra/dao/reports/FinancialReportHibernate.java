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
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.reports.FinancialByDoctorReportData;
import br.com.easyclinica.domain.entities.reports.FinancialByHealthCarePlanReportData;
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
	

	@SuppressWarnings("unchecked")
	public List<FinancialByDoctorReportData> financialByDoctor(Calendar start,
			Calendar end) {
		Criteria criteria = session
				.createCriteria(Appointment.class)
				.add(Restrictions.between("appointmentDate", start, end))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.rowCount(), "qtyAppointments")
								.add(Projections.sum("procedureAmount"),
										"procedureAmount")
								.add(Projections.sum("appointmentAmount"),
										"appointmentAmount")
								.add(Projections.sum("roomRateAmount"),
										"roomRateAmount")
								.add(Projections.property("doctor"), "doctor")
								.add(Projections.groupProperty("doctor")))
				.setResultTransformer(
						Transformers
								.aliasToBean(FinancialByDoctorReportData.class))
				.createCriteria("doctor").addOrder(Order.asc("name"));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<FinancialByHealthCarePlanReportData> financialByHealthCarePlan(
			Calendar start, Calendar end) {
		Criteria criteria = session
				.createCriteria(Appointment.class)
				.add(Restrictions.between("appointmentDate", start, end))
				.setProjection(
						Projections
								.projectionList()
								.add(Projections.rowCount(), "qtyAppointments")
								.add(Projections.sum("procedureAmount"),
										"procedureAmount")
								.add(Projections.sum("appointmentAmount"),
										"appointmentAmount")
								.add(Projections.sum("roomRateAmount"),
										"roomRateAmount")
								.add(Projections.property("healthCarePlan"), "healthCarePlan")
								.add(Projections.groupProperty("healthCarePlan")))
				.setResultTransformer(
						Transformers
								.aliasToBean(FinancialByHealthCarePlanReportData.class))
				.createCriteria("healthCarePlan").addOrder(Order.asc("name"));

		return criteria.list();
	}
	private Order byName() {
		return Order.asc("name");
	}

	private Order byDate() {
		return Order.asc("appointmentDate");
	}


}
