package org.thinpole;

public class A {
	public static void main(String[] args) throws Exception {

		S.dropDb();
		S.createDb();
		
		
		
		/*String url = "jdbc:derby:zoo";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * from animal");
			while (rs.next()) {
				H.s("");
			}
		}*/
		
	}
}