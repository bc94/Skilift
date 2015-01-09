package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Backend {

	public void addUser(User user){
		
	}
	
	public Boolean isRegistered(User user){
		
		return true;
	}
	
	public Liftstation getStation(String name){
		
		return new Liftstation("dummy", "dummy", "dummy","dummy", new ArrayList<Barrier>());
	}
	
	private void readXLSTable(File table){
		
	}
	public static void main(String[] args) {

	}

}
