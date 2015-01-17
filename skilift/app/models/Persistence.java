package models;

import java.io.File;

import java.util.List;

import com.avaje.ebean.Ebean;
import controllers.Backend;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Persistence implements Backend {
		
	private String dbname;
	
	public Persistence(String dbname) {

	
	}

	@Override
	/** FIXME error handling */
	public void registerUser(User user){
		if (isRegistered(user.mail)) {
			user.save();
		}
	}

	@Override
	public boolean isRegistered(String email){
		return false;
	}

	@Override
	public Liftstation getStation(String name) {
		return null;
	}


	private void readXLSTable(File table){
		
		
	}

	@Override
	public List<Liftstation> getFavourites(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFavourite(User user, Liftstation lift) {

	}

}
