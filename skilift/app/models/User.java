package models;

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

	public User(String mail, String password, String paymentMethod) {
		super();
		this.mail = mail;
		this.password = password;
		this.paymentMethod = paymentMethod;
		favourites = new ArrayList<Liftstation>();
	}

	public String validate() {
		if (authenticate(mail, password)) {
			return "Invalid email or password";
		}
		return null;
	}

	private boolean authenticate(String mail, String password) {
		// TODO: db query
		return false;
	}

	public String changePW(String oldPW, String newPW) {

		if(oldPW.equals(password)){
			password = newPW;
			update();
			return ("Successfully changed password!");
		}else{
			
			update();
			return ("Wrong password, try again.");
		}

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
		save();
	}

	
	public void removeFavourite(Liftstation ls) {
		
		favourites.remove(ls);
		update();

	}
	
    public Boolean isFav(Liftstation station){
    	
    	return favourites.contains(station);
    		
    }
}
