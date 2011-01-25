package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_16 implements Migration{


	public void down() {
		dropTable("appointment_procedure");
	}

	public void up() {
		createTable(table("appointment_procedure",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("appointment_fk", INTEGER, notnull()),
				column("procedure_fk", INTEGER, notnull()),
				column("is_fixed_amount", BOOLEAN, notnull(), defaultValue(0)),
				column("amount", DOUBLE, notnull(), defaultValue(0))
		));
	}
}
