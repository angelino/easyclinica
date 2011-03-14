package br.com.easyclinica.database;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.junit.Ignore;
import org.junit.Test;

import br.com.easyclinica.infra.database.DatabaseConfigurator;


public class ExportTables {

	@Test @Ignore
	public void createNewSchema() {
		Configuration cfg = DatabaseConfigurator.config("easyclinicatest");
		
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}
	
	@Test @Ignore
	public void updateSchema() {
		Configuration cfg = DatabaseConfigurator.config("easyclinicatest");
		
		SchemaUpdate se = new SchemaUpdate(cfg);
		se.execute(true, true);
		
	}
}
