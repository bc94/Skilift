package models;

import controllers.Backend;
import controllers.Request;
import play.db.ebean.Model;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.util.List;

@Entity
public class User extends Model {

	@Id
	public String mail;
	public String password;
	public String paymentMethod;
	public List<Liftstation> favourites;
	private Backend backend;
	
	
	public User(String mail, String password, String paymentMethod) {
		super();
		this.mail = mail;
		this.password = password;
		this.paymentMethod = paymentMethod;
		favourites = backend.getFavourites(this);
	}

	public void changePW(String oldPW, String newPW) {
		// TODO Auto-generated method stub

	}

	public static Finder<String,User> find = new Finder<String,User>(
			String.class, User.class
	);


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
