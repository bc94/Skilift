package models;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import views.html.main;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Persistence implements Backend {
		
	private String dbname;
	
	public Persistence(String dbname) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.dbname = dbname;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void registerUser(User user){
		try  (Connection dbconnection = DriverManager.getConnection("jdbc:sqlite:" + dbname)) {
			Statement statement = dbconnection.createStatement();
			statement.executeUpdate("INSERT INTO User VALUES (" + user.getMail() + ", " + user.getPassword() + ", " + user.getPaymentMethod() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isRegistered(User user){
		try  (Connection dbconnection = DriverManager.getConnection("jdbc:sqlite:" + dbname)) {
			Statement statement = dbconnection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT email FROM User WITH email = '" + user.getMail() + "'" );
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Liftstation getStation(String name){
		
		return new Liftstation("dummy", "dummy", "dummy", 0, null);
	}
	
	public void initDB() {
		
		try  (Connection dbconnection = DriverManager.getConnection("jdbc:sqlite:" + dbname)) {
			Statement statement = dbconnection.createStatement();
			statement.executeUpdate("CREATE TABLE User (mail VARCHAR(50) PRIMARY KEY, password VARCHAR(20) NOT NULL, paymentMethod VARCHAR(20))");
			statement.executeUpdate("CREATE TABLE Skilift (ID INT PRIMARY KEY, name VARCHAR(50) NOT NULL, plz INT NOT NULL, type VARCHAR(20) NOT NULL,capacity INT NOT NULL)");
			statement.executeUpdate("CREATE TABLE Favourite (mail VARCHAR(50), ID INT, FOREIGN KEY (mail) REFERENCES User(mail), FOREIGN KEY (ID) REFERENCES Skilift(ID))");
			readXLSTable(new File("Aufstiegshilfen.xls"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void readXLSTable(File table){
		
		try (Connection dbconnection = DriverManager
				.getConnection("jdbc:sqlite:" + dbname)) {
			Sheet skilifts = Workbook.getWorkbook(table).getSheet(0);
			Cell name, ID, plz, type, capacity;
			PreparedStatement insert = dbconnection.prepareStatement("INSERT INTO Skilift VALUES (?,?,?,?,?)");
			for (int n = skilifts.getRows(), i = 3; i<n;i++) {
				name = skilifts.getCell(0,i);
				ID = skilifts.getCell(1,i);
				plz = skilifts.getCell(2,i);
				type = skilifts.getCell(10,i);
				capacity = skilifts.getCell(14,i);
				
				insert.setInt(1, Integer.parseInt(ID.getContents()));
				insert.setString(2, name.getContents());
				if (plz.getContents().isEmpty())
					insert.setInt(3, 0);
				else
					insert.setInt(3, Integer.parseInt(plz.getContents()));
				insert.setString(4, type.getContents());
				if (capacity.getContents().isEmpty())
					insert.setInt(5, 0);
				else
					insert.setInt(5, Integer.parseInt(capacity.getContents()));;
				
				insert.executeUpdate();
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Liftstation> getFavourites(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFavourite(User user, Liftstation lift) {
		try  (Connection dbconnection = DriverManager.getConnection("jdbc:sqlite:" + dbname)) {
			Statement statement = dbconnection.createStatement();
			statement.executeUpdate("INSERT INTO Favourite VALUES (" + user.getMail() + ", " + lift.getID() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
