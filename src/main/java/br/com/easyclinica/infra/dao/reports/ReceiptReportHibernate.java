package br.com.easyclinica.infra.dao.reports;

import java.util.Calendar;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.reports.ReceiptReportGenerator;

@Component
public class ReceiptReportHibernate implements ReceiptReportGenerator {

	private final Session session;

	public ReceiptReportHibernate(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<Receipt> generate(Calendar start, Calendar end) {
		return session.createCriteria(Receipt.class)
				.add(Restrictions.between("date", start, end))
				.addOrder(Order.asc("date"))
				.setFetchMode("patient", FetchMode.JOIN)
				.list();
	}
}
