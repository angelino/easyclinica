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
public class MaterialPricingSheetExporter implements StuffExporter {

	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final HeaderCreator header;
	private final PrecifiedThings precifiedThings;

	public MaterialPricingSheetExporter(HeaderCreator header, PrecifiedThings precifiedThings) {
		this.header = header;
		this.precifiedThings = precifiedThings;
	}

	public void export(HealthCarePlan plan, HSSFWorkbook wb) {
		this.plan = plan;
		this.wb = wb;

		exportMaterials();
	}

	private void exportMaterials() {
		Sheet sheet = wb.createSheet("Materiais");

		header.create(sheet, "ID", "Nome do Material", "Valor");

		int rowCount = 1;
		
		for(PricedStuff stuff : precifiedThings.getMaterialsPrice(plan)) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(stuff.getId());
			row.createCell(1).setCellValue(stuff.getName());
			row.createCell(2).setCellValue(stuff.getAmount().doubleValue());
		}
		
	}

	public String getName() {
		return "material";
	}

}
