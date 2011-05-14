package br.com.easyclinica.domain.services.pricing.exporter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.repositories.AllMedicines;
import br.com.easyclinica.domain.services.pricing.HeaderCreator;

@Component
public class MedicinePricingSheetExporter implements StuffExporter {

	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final AllMedicines medicines;
	private final HeaderCreator header;

	public MedicinePricingSheetExporter(AllMedicines medicines, HeaderCreator header) {
		this.medicines = medicines;
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
		for(Medicine medicine : medicines.getAll()) {
			PrecifiedMedicine precifiedMedicine = findInPlan(medicine);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(medicine.getId());
			row.createCell(1).setCellValue(medicine.getName());
			row.createCell(2).setCellValue((precifiedMedicine == null ? 0.0 : precifiedMedicine.getAmount().doubleValue())); 
		}
	}


	private PrecifiedMedicine findInPlan(Medicine medicine) {
		for(PrecifiedMedicine pm : plan.getPrecifiedMedicines()) {
			if(pm.getMedicine().getId() == medicine.getId()) return pm;
		}
		
		return null;
	}

	public String getName() {
		return "medicine";
	}
	
}
