package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.DataTypes.INTEGER;
import static com.eroi.migrate.Execute.dropColumn;
import static com.eroi.migrate.Execute.addColumn;
import com.eroi.migrate.Migration;

public class Migration_6 implements Migration {

	public void down() {
		dropColumn("table_fk", "healthcareplan");
	}

	public void up() {
		addColumn(column("table_fk", INTEGER, notnull()), "healthcareplan");
	}

}
