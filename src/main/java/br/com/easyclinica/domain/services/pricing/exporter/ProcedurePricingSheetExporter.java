package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.pricing.PricedProcedure;
import br.com.easyclinica.domain.repositories.PrecifiedThings;
import br.com.easyclinica.domain.services.pricing.HeaderCreator;

@Component
public class ProcedurePricingSheetExporter implements StuffExporter {

	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final HeaderCreator header;
	private final PrecifiedThings precifiedThings;

	public ProcedurePricingSheetExporter(HeaderCreator header, PrecifiedThings precifiedThings) {
		this.header = header;
		this.precifiedThings = precifiedThings;
	}

	public void export(HealthCarePlan plan, HSSFWorkbook wb) {
		this.plan = plan;
		this.wb = wb;

		exportProcedures();
	}

	private void exportProcedures() {
		Sheet sheet = wb.createSheet("Procedimentos");

		header.create(sheet, "ID", "Codigo AMB", "Nome do Procedimento",
				"Valor Fixo", "CHs", "Taxa de sala");

		int rowCount = 1;
		
		for(PricedProcedure stuff : precifiedThings.getProceduresPrice(plan)) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(stuff.getId());
			row.createCell(1).setCellValue(stuff.getAmbCode());
			row.createCell(2).setCellValue(stuff.getName());
			row.createCell(3).setCellValue(stuff.getAmount() == null ? 0.0 : stuff.getAmount().doubleValue());
			row.createCell(4).setCellValue(stuff.getCh());
			row.createCell(5).setCellValue(stuff.getRoomTaxAmount() == null ? 0.0 : stuff.getRoomTaxAmount().doubleValue());
		}

	}

	public String getName() {
		return "procedure";
	}

}
