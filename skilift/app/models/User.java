package models;

import controllers.Backend;
import controllers.Request;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Model {

	@Id
	@Required
	public String mail;
	@Required
	public String password;
	public String paymentMethod;
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<Liftstation> favourites;

	public static void create(User user) {
		user.save();
	}

	public User(String mail, String password, String paymentMethod) {
		super();
		this.mail = mail;
		this.password = password;
		this.paymentMethod = paymentMethod;
		favourites = new ArrayList<>();
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
