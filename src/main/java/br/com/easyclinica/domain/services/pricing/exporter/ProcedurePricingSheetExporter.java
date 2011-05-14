package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.services.pricing.HeaderCreator;

@Component
public class ProcedurePricingSheetExporter implements StuffExporter {

	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final AllProcedures procedures;
	private final HeaderCreator header;

	public ProcedurePricingSheetExporter(AllProcedures procedures, HeaderCreator header) {
		this.procedures = procedures;
		this.header = header;
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
		for (Procedure procedure : procedures.getAll()) {
			PrecifiedProcedure precifiedProcedure = findInPlan(procedure);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(procedure.getId());
			row.createCell(1).setCellValue(procedure.getAmbCode());
			row.createCell(2).setCellValue(procedure.getName());
			row.createCell(3).setCellValue(
					(precifiedProcedure == null ? 0.0 : precifiedProcedure
							.getFixedAmount().doubleValue()));
			row.createCell(4).setCellValue(
					(precifiedProcedure == null ? 0 : precifiedProcedure
							.getCh()));
			row.createCell(5).setCellValue(
					(precifiedProcedure == null ? 0 : precifiedProcedure
							.getRoomTaxAmount().doubleValue()));
		}
	}

	private PrecifiedProcedure findInPlan(Procedure procedure) {
		for (PrecifiedProcedure pp : plan.getPrecifiedProcedures()) {
			if (pp.getProcedure().getId() == procedure.getId())
				return pp;
		}

		return null;
	}

	public String getName() {
		return "procedure";
	}

}
