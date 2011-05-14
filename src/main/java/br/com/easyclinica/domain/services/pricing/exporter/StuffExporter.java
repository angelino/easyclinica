package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.easyclinica.domain.entities.HealthCarePlan;

public interface StuffExporter {
	void export(HealthCarePlan plan, HSSFWorkbook wb);
	String getName();
}
