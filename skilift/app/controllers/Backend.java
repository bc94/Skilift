package controllers;

import models.Liftstation;
import models.User;

import java.util.List;

public interface Backend {
	
	void registerUser(User user);
	
	boolean isRegistered(String email);
	
	Liftstation getStation(String name);
	
	void addFavourite(User user, Liftstation lift);

	List<Liftstation> getFavourites(User user);

}
