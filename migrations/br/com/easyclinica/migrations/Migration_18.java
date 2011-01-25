package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_18 implements Migration{


	public void down() {
		dropTable("appointment_procedure_medicine");
	}

	public void up() {
		createTable(table("appointment_procedure_medicine",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("appointment_procedure_fk", INTEGER, notnull()),
				column("medicine_fk", INTEGER, notnull()),
				column("unit_amount", DOUBLE, notnull()),
				column("total_amount", DOUBLE, notnull()),
				column("qty", FLOAT, notnull())
		));
	}
}
