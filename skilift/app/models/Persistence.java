package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Persistence implements Backend {

	public void addUser(User user){
		
	}
	
	public Boolean isRegistered(User user){
		
		return true;
	}
	
	public Liftstation getStation(String name){
		
		return new Liftstation("dummy", "dummy", "dummy","dummy", new ArrayList<Barrier>());
	}
	
	public void readXLSTable(File table){
		
	}

}
