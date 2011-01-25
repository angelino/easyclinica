package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_12 implements Migration{


	public void down() {
		dropTable("procedure_medicine");
	}

	public void up() {
		createTable(table("procedure_medicine",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("procedure_fk", INTEGER, notnull()),
				column("medicine_fk", INTEGER, notnull()),
				column("qty", FLOAT, notnull())
		));
	}
}
