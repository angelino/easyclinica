package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_15 implements Migration{


	public void down() {
		dropTable("healthcareplan_procedure");
	}

	public void up() {
		createTable(table("healthcareplan_procedure",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("healthcareplan_fk", INTEGER, notnull()),
				column("procedure_fk", INTEGER, notnull()),
				column("fixed_amount", DOUBLE, notnull(), defaultValue(0))
		));
	}
}
