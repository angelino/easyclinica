package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.domain.services.pricing.HeaderCreator;

@Component
public class SpecialtyPricingSheetExporter implements StuffExporter {

	private final AllSpecialties specialties;
	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final HeaderCreator header;

	public SpecialtyPricingSheetExporter(AllSpecialties specialties, HeaderCreator header) {
		this.specialties = specialties;
		this.header = header;
	}

	public void export(HealthCarePlan plan, HSSFWorkbook wb) {
		this.plan = plan;
		this.wb = wb;

		exportSpecialties();
	}

	private void exportSpecialties() {
		Sheet sheet = wb.createSheet("Especialidades");

		header.create(sheet, "ID", "Nome da Especialidade", "Valor");

		int rowCount = 1;
		for (Specialty specialty : specialties.getAll()) {
			PrecifiedSpecialty precifiedSpecialty = findInPlan(specialty);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(specialty.getId());
			row.createCell(1).setCellValue(specialty.getName());
			row.createCell(2).setCellValue(
					(precifiedSpecialty == null ? 0.0 : precifiedSpecialty
							.getAmount().doubleValue()));
		}
	}

	private PrecifiedSpecialty findInPlan(Specialty specialty) {
		for (PrecifiedSpecialty ps : plan.getPrecifiedSpecialties()) {
			if (ps.getSpecialty().getId() == specialty.getId())
				return ps;
		}
		return null;
	}

	public String getName() {
		return "specialty";
	}

}
