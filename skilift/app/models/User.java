package models;

import java.util.List;

public class User {

	private String mail;
	private String password;
	private String paymentMethod;
	private List<Liftstation> favourites;
	private Backend backend;
	
	
	public User(String mail, String password, String paymentMethod) {
		super();
		this.mail = mail;
		this.password = password;
		this.paymentMethod = paymentMethod;
		favourites = backend.getFavourites(this);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Liftstation> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Liftstation> favourites) {
		this.favourites = favourites;
	}


	public void changePW(String oldPW, String newPW) {
		// TODO Auto-generated method stub

	}

	
	public void changePayment(String pm) {
		// TODO Auto-generated method stub

	}

	public void addFavourite(Liftstation ls) {
		// TODO Auto-generated method stub

	}

	
	public void removeFavourite(Liftstation ls) {
		// TODO Auto-generated method stub

	}

	public void giveFeedback(String fb) {
		// TODO Auto-generated method stub

	}

	public Request rentRequest(Liftstation ls) {
		// TODO Auto-generated method stub
		return null;
	}
}
