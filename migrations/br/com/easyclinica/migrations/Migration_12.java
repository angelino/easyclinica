package br.com.easyclinica.migrations;

import com.eroi.migrate.Migration;
import static com.eroi.migrate.Define.*;
import static com.eroi.migrate.Define.DataTypes.*;
import static com.eroi.migrate.Execute.*; 

public class Migration_12 implements Migration {

	public void down() {
		dropTable("patient");
	}

	public void up() {
		createTable(table("patient",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("name", VARCHAR, length(50), notnull()),
				column("street", VARCHAR, length(150)),
				column("neighborhood", VARCHAR, length(100)),
				column("postalCode", VARCHAR, length(12)),
				column("city", VARCHAR, length(50)),
				column("state", VARCHAR, length(2)),
				column("telephone", VARCHAR, length(50)),
				column("cellphone", VARCHAR, length(50)),
				column("email", VARCHAR, length(100)),
				column("healthcareid", VARCHAR, length(100)),
				column("healthcareplan_fk", INTEGER, notnull()),
				column("observations", com.eroi.migrate.Define.DataTypes.LONGVARCHAR)
		));
	}

}
