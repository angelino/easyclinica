package br.com.easyclinica.database;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Ignore;
import org.junit.Test;

import br.com.easyclinica.infra.database.DatabaseConfigurator;


public class ExportTables {

	@Test @Ignore
	public void exportDatabase() {
		Configuration cfg = DatabaseConfigurator.config("easyclinica");
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
}
