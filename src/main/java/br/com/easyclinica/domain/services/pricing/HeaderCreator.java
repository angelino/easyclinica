package br.com.easyclinica.domain.services.pricing;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class HeaderCreator {
	public void create(Sheet sheet, String... colunas) {
		Row row = sheet.createRow(0);
		int columnCount = 0;
		for (String coluna : colunas) {
			row.createCell(columnCount++).setCellValue(coluna);
		}
	}
}
