package models;

import com.avaje.ebean.annotation.ConcurrencyMode;
import com.avaje.ebean.annotation.EntityConcurrencyMode;
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

	public User() {
		favourites = new ArrayList<Liftstation>();
	}

	public User(String mail, String password, String paymentMethod) {
		super();
		this.mail = mail;
		this.password = password;
		this.paymentMethod = paymentMethod;
		favourites = new ArrayList<Liftstation>();
	}



	public void changePW(String newPW) {

			password = newPW;
			update();
	}

	public static Finder<String,User> find = new Finder<String,User>(
			String.class, User.class
	);


	public void changePayment(String pm) {
		
		paymentMethod = pm;
		update();
	}

	public void addFavourite(Liftstation ls) {
		
		favourites.add(ls);
		update();
	}

	
	public void removeFavourite(Liftstation ls) {
		
		favourites.remove(ls);
		update();

	}
	
    public Boolean isFav(Liftstation station){
    	
    	return favourites.contains(station);
    		
    }

	public static boolean authenticate(String email, String password) {
		User wanted = User.find.byId(email);
		System.out.println("email: "+ email + "password: " + password);
		if (wanted == null || !wanted.password.equals(password))
			return false;
		return true;
	}

	public String validate() {
		if (User.find.byId(mail) != null) {
			return "A user with this email was already registered";
		}
		return null;
	}
}
