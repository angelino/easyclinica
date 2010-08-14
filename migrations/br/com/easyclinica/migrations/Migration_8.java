package br.com.easyclinica.migrations;

import static com.eroi.migrate.Define.column;
import static com.eroi.migrate.Define.notnull;
import static com.eroi.migrate.Define.DataTypes.DOUBLE;
import static com.eroi.migrate.Execute.dropColumn;
import static com.eroi.migrate.Execute.addColumn;
import com.eroi.migrate.Migration;

public class Migration_8 implements Migration {

	public void down() {
		dropColumn("ch", "healthcareplan");
	}

	public void up() {
		addColumn(column("amount", DOUBLE, notnull()), "healthcareplan");
	}

}
