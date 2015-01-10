package models;

import java.util.List;

public interface Backend {
	
	void registerUser(User user);
	
	boolean isRegistered(User user);
	
	Liftstation getStation(String name);
	
	void addFavourite(User user, Liftstation lift);

	List<Liftstation> getFavourites(User user);

}
