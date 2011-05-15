package br.com.easyclinica.domain.reports;

import java.util.Calendar;
import java.util.List;

import br.com.easyclinica.domain.entities.Receipt;

public interface ReceiptReportGenerator {
	List<Receipt> generate(Calendar start, Calendar end);
}
