package models;

public interface User {

	public void changePW(String oldPW, String newPW);
	public void changePayment(String pm);
	public void addFavourite(Liftstation ls);
	public void removeFavourite(Liftstation ls);
	public void giveFeedback(String fb);
	public Request rentRequest(Liftstation ls);
	
	public static void main(String[] args) {	

	}

}
