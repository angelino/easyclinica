package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.pricing.PricedStuff;
import br.com.easyclinica.domain.repositories.PrecifiedThings;
import br.com.easyclinica.domain.services.pricing.HeaderCreator;

@Component
public class MedicinePricingSheetExporter implements StuffExporter {

	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final HeaderCreator header;
	private final PrecifiedThings precifiedThings;

	public MedicinePricingSheetExporter(HeaderCreator header, PrecifiedThings precifiedThings) {
		this.precifiedThings = precifiedThings;
		this.header = header;
	}
	
	public void export(HealthCarePlan plan, HSSFWorkbook wb) {
			this.plan = plan;
			this.wb = wb;

			exportMedicines();
	}

	private void exportMedicines() {
		Sheet sheet = wb.createSheet("Medicamentos");
		
		header.create(sheet, "ID", "Nome do Medicamento", "Valor");
		
		int rowCount = 1;
		
		for(PricedStuff stuff : precifiedThings.getMedicinesPrice(plan)){
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(stuff.getId());
			row.createCell(1).setCellValue(stuff.getName());
			row.createCell(2).setCellValue(stuff.getAmount().doubleValue()); 
		}		
	}

	public String getName() {
		return "medicine";
	}
	
}
