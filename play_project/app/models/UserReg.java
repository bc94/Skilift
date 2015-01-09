package models;

import java.util.List;

public class UserReg implements User {

	private String mail;
	private String password;
	private String paymentMethod;
	private List<Liftstation> favourites;
	
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

	@Override
	public void changePW(String oldPW, String newPW) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePayment(String pm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFavourite(Liftstation ls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFavourite(Liftstation ls) {
		// TODO Auto-generated method stub

	}

	@Override
	public void giveFeedback(String fb) {
		// TODO Auto-generated method stub

	}

	@Override
	public Request rentRequest(Liftstation ls) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
