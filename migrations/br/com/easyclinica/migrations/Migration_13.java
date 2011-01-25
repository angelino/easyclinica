package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_13 implements Migration{


	public void down() {
		dropTable("healthcareplan_material");
	}

	public void up() {
		createTable(table("healthcareplan_material",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("healthcareplan_fk", INTEGER, notnull()),
				column("material_fk", INTEGER, notnull()),
				column("amount", DOUBLE, notnull())
		));
	}
}
