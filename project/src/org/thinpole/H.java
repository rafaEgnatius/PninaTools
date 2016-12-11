package org.thinpole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class H {
	public static void s(Object toPrint) {
		System.out.println(toPrint);
	}

	public static void s() {
		System.out.println();
	}

	public static void sRS(ResultSet rs, int tableSize) throws SQLException {
		while (rs.next()) {
			for (int i = 1; i < (tableSize + 1); i++) {
				System.out.print(rs.getObject(i) + " ");
			}
			s();
		}
	}
}