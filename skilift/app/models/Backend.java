package models;

import java.io.File;

public interface Backend {
	
	void addUser(User user);
	
	Boolean isRegistered(User user);
	
	Liftstation getStation(String name);
	
	void readXLSTable(File table);
}
