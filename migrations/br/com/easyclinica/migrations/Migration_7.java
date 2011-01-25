package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Define.DataTypes.*;
import static com.eroi.migrate.Execute.*;

import com.eroi.migrate.Migration;

public class Migration_7 implements Migration {

	public void down() {
		dropTable("healthcareplan_specialty");
	}

	public void up() {
		createTable(table("specialty",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("healthcareplan_fk", INTEGER, notnull()),
				column("specialty_fk", INTEGER, notnull()),
				column("amount", DOUBLE, notnull(), defaultValue(0))
		));
	}
}
