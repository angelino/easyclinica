package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.autoincrement;
import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.defaultValue;
import static com.eroi.migrate.Define.length;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.primarykey;
import static com.eroi.migrate.Define.table;
import static com.eroi.migrate.Define.DataTypes.INTEGER;
import static com.eroi.migrate.Define.DataTypes.VARCHAR;
import static com.eroi.migrate.Execute.createTable;
import static com.eroi.migrate.Execute.dropTable;

import com.eroi.migrate.Migration;

public class Migration_13 implements Migration  {
	public void down() {
		dropTable("schedule");
	}

	public void up() {
		createTable(table("schedule",
				column("id", INTEGER, primarykey(), notnull(), autoincrement()),
				column("doctor_fk", INTEGER, notnull()),
				column("patient_fk", INTEGER, notnull()),
				column("description", com.eroi.migrate.Define.DataTypes.LONGVARCHAR),
				column("reason", INTEGER, notnull(), defaultValue(1)),
				column("day", com.eroi.migrate.Define.DataTypes.DATE),
				column("start", VARCHAR, length(6), defaultValue("00:00")),
				column("end", VARCHAR, length(6), defaultValue("00:00")),
				column("telephone", VARCHAR, length(50))
		));
	}
	
}
