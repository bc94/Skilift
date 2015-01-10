import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Persistence;

public class PersistenceTest {
	
	static Persistence p;
	
	@BeforeClass
	public static void setUp() {
		p = new Persistence("test.db");
	}
	
	@Test
	public void testXlsReader() {
		p.initDB();
		
		try  (Connection dbconnection = DriverManager.getConnection("jdbc:sqlite:" + "test.db")) {
			Statement statement = dbconnection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Skilift");
			while(rs.next()) {
				System.out.println(rs.getInt("ID") + "\t" + rs.getString("name") + "\t" + rs.getInt("plz") + "\t" + rs.getString("type") + "\t" + rs.getInt("capacity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@AfterClass
	public static void cleanUp() {
		(new File("test.db")).delete();
	}
}
