package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Execute.*;
import static com.eroi.migrate.Define.DataTypes.*;

import com.eroi.migrate.Migration;

public class Migration_11 implements Migration{


	public void down() {
		dropTable("procedure_material");
	}

	public void up() {
		createTable(table("procedure_material",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("procedure_fk", INTEGER, notnull()),
				column("material_fk", INTEGER, notnull()),
				column("qty", FLOAT, notnull())
		));
	}
}
