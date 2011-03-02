package br.com.easyclinica.domain.reports;

import java.util.List;

import br.com.easyclinica.domain.entities.Appointment;

public interface ReportGenerator {
	List<Appointment> financial();
}
