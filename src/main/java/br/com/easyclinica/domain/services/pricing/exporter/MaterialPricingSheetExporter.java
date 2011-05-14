package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.repositories.AllMaterials;
import br.com.easyclinica.domain.services.pricing.HeaderCreator;

@Component
public class MaterialPricingSheetExporter implements StuffExporter {

	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final AllMaterials materials;
	private final HeaderCreator header;

	public MaterialPricingSheetExporter(AllMaterials materials, HeaderCreator header) {
		this.materials = materials;
		this.header = header;
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
		for (Material material : materials.getAll()) {
			PrecifiedMaterial precifiedMaterial = findInPlan(material);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(material.getId());
			row.createCell(1).setCellValue(material.getName());
			row.createCell(2).setCellValue(
					(precifiedMaterial == null ? 0.0 : precifiedMaterial
							.getAmount().doubleValue()));
		}
	}

	private PrecifiedMaterial findInPlan(Material material) {
		for (PrecifiedMaterial pm : plan.getPrecifiedMaterials()) {
			if (pm.getMaterial().getId() == material.getId())
				return pm;
		}

		return null;
	}

	public String getName() {
		return "material";
	}

}
