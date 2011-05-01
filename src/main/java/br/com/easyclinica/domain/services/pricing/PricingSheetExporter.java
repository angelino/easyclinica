package br.com.easyclinica.domain.services.pricing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.repositories.AllMaterials;
import br.com.easyclinica.domain.repositories.AllMedicines;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.repositories.AllSpecialties;

@Component
public class PricingSheetExporter {

	private final AllSpecialties specialties;
	private HealthCarePlan plan;
	private HSSFWorkbook wb;
	private final AllProcedures procedures;
	private final AllMaterials materials;
	private final AllMedicines medicines;

	public PricingSheetExporter(AllSpecialties specialties, AllProcedures procedures, AllMedicines medicines, AllMaterials materials) {
		this.specialties = specialties;
		this.procedures = procedures;
		this.medicines = medicines;
		this.materials = materials;
	}
	
	public void priceList(HealthCarePlan plan, ByteArrayOutputStream fileOut) {
		try {
			this.plan = plan;

			wb = new HSSFWorkbook();

			exportSpecialties();
			exportProcedures();
			exportMaterials();
			exportMedicines();
			
			wb.write(fileOut);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void exportProcedures() throws IOException {
		Sheet sheet = wb.createSheet("Procedimentos");
		
		createHeader(sheet, "ID", "Codigo AMB", "Nome do Procedimento", "Valor Fixo", "CHs", "Taxa de sala");
		
		int rowCount = 1;
		for(Procedure procedure : procedures.getAll()) {
			PrecifiedProcedure precifiedProcedure = findInPlan(procedure);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(procedure.getId());
			row.createCell(1).setCellValue(procedure.getAmbCode());
			row.createCell(2).setCellValue(procedure.getName());
			row.createCell(3).setCellValue((precifiedProcedure == null ? 0.0 : precifiedProcedure.getFixedAmount().doubleValue())); 
			row.createCell(4).setCellValue((precifiedProcedure == null ? 0 : precifiedProcedure.getCh())); 
			row.createCell(5).setCellValue((precifiedProcedure == null ? 0 : precifiedProcedure.getRoomTaxAmount().doubleValue())); 
		}
	}

	private void exportSpecialties() throws IOException {
		Sheet sheet = wb.createSheet("Especialidades");
		
		createHeader(sheet, "ID", "Nome da Especialidade", "Valor");
		
		int rowCount = 1;
		for(Specialty specialty : specialties.getAll()) {
			PrecifiedSpecialty precifiedSpecialty = findInPlan(specialty);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(specialty.getId());
			row.createCell(1).setCellValue(specialty.getName());
			row.createCell(2).setCellValue((precifiedSpecialty == null ? 0.0 : precifiedSpecialty.getAmount().doubleValue())); 
		}
	}

	private void exportMedicines() throws IOException {
		Sheet sheet = wb.createSheet("Remédios");
		
		createHeader(sheet, "ID", "Nome do Medicamento", "Valor");
		
		int rowCount = 1;
		for(Medicine medicine : medicines.getAll()) {
			PrecifiedMedicine precifiedMedicine = findInPlan(medicine);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(medicine.getId());
			row.createCell(1).setCellValue(medicine.getName());
			row.createCell(2).setCellValue((precifiedMedicine == null ? 0.0 : precifiedMedicine.getAmount().doubleValue())); 
		}
	}

	private void exportMaterials() throws IOException {
		Sheet sheet = wb.createSheet("Materiais");
		
		createHeader(sheet, "ID", "Nome do Material", "Valor");
		
		int rowCount = 1;
		for(Material material : materials.getAll()) {
			PrecifiedMaterial precifiedMaterial = findInPlan(material);
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(material.getId());
			row.createCell(1).setCellValue(material.getName());
			row.createCell(2).setCellValue((precifiedMaterial == null ? 0.0 : precifiedMaterial.getAmount().doubleValue())); 
		}
	}

	private PrecifiedSpecialty findInPlan(Specialty specialty) {
		for(PrecifiedSpecialty ps : plan.getPrecifiedSpecialties()) {
			if(ps.getSpecialty().getId() == specialty.getId()) return ps;
		}
		return null;
	}

	private PrecifiedMedicine findInPlan(Medicine medicine) {
		for(PrecifiedMedicine pm : plan.getPrecifiedMedicines()) {
			if(pm.getMedicine().getId() == medicine.getId()) return pm;
		}
		
		return null;
	}
	
	private PrecifiedMaterial findInPlan(Material material) {
		for(PrecifiedMaterial pm : plan.getPrecifiedMaterials()) {
			if(pm.getMaterial().getId() == material.getId()) return pm;
		}
		
		return null;
	}

	private PrecifiedProcedure findInPlan(Procedure procedure) {
		for(PrecifiedProcedure pp : plan.getPrecifiedProcedures()) {
			if(pp.getProcedure().getId() == procedure.getId()) return pp;
		}
		
		return null;
	}
	
	private void createHeader(Sheet sheet, String... colunas) {
		Row row = sheet.createRow(0);
		int columnCount = 0;
		for(String coluna : colunas) {
			row.createCell(columnCount++).setCellValue(coluna);
		}
	}
}
