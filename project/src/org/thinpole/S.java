package org.thinpole;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class S {
	public static void createDb() throws Exception {
		String url = "jdbc:derby:zoo;create=true";
		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("CREATE TABLE species ("
					+ "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) , "
					+ "name VARCHAR(255), " + "num_acres DECIMAL)");
			stmt.executeUpdate("CREATE TABLE animal ("
					+ "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) , "
					+ "species_id integer REFERENCES species(id), " + "name VARCHAR(255), " + "date_born TIMESTAMP)");
			stmt.executeUpdate("INSERT INTO species (name, num_acres) VALUES ('African Elephant', 7.5)");
			stmt.executeUpdate("INSERT INTO species (name, num_acres) VALUES ('Zebra', 1.2)");
			stmt.executeUpdate(
					"INSERT INTO animal (SPECIES_ID, NAME, DATE_BORN)  VALUES (1, 'Elsa', '2001-05-06 02:15:00')");
			stmt.executeUpdate(
					"INSERT INTO animal (SPECIES_ID, NAME, DATE_BORN)  VALUES (2, 'Zelda', '2002-08-15 09:12:00')");
			stmt.executeUpdate(
					"INSERT INTO animal (SPECIES_ID, NAME, DATE_BORN)  VALUES (1, 'Ester', '2002-09-09 10:36:00')");
			stmt.executeUpdate(
					"INSERT INTO animal (SPECIES_ID, NAME, DATE_BORN)  VALUES (1, 'Eddie', '2010-06-08 01:24:00')");
			stmt.executeUpdate(
					"INSERT INTO animal (SPECIES_ID, NAME, DATE_BORN)  VALUES (2, 'Zoe', '2005-11-12 03:44:00')");
		}
		H.s("");
		H.s("Database created");
	}

	public static void dropDb() throws Exception {
		Path path = Paths.get("zoo");
		// H.s(path.toAbsolutePath());
		Files.walkFileTree(path.toAbsolutePath(), new DeletePath());
	}

	public static class DeletePath extends SimpleFileVisitor<Path> {
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			System.out.println("Deleting file: " + file.toAbsolutePath());
			Files.delete(file);
			return FileVisitResult.CONTINUE;
		}

		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			if (exc != null) {
				System.out.println("Exception encountered " + exc.toString());
				throw exc;
			}
			System.out.println("Deleting directory: " + dir.toAbsolutePath());
			Files.delete(dir);
			return FileVisitResult.CONTINUE;
		}
	}
}
