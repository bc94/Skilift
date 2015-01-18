import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.User;
import models.Liftstation;
import controllers.Barrier;
import java.util.ArrayList;
/**
 * 
 * Simple JUnit test case for the User model class.
 */

public class UserTest {

	User user = new User("testMail", "abcd", "paypal");

	@Test
	public void passChange() {
		
		assertEquals(user.changePW("abcd", "efgh"), "Successfully changed password!");
		assertEquals(user.changePW("abcd", "efgh"), "Wrong password, try again.");
	}
	
	@Test
	public void payChange() {
		
		user.changePayment("test");
		assertEquals(user.paymentMethod, "test");
	}
	
	@Test 
	public void addFav(){
		
		Liftstation ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
		user.addFavourite(ls);
		assertEquals(ls, user.favourites.get(0));
	}
	
	@Test
	public void remFav(){
		
		assertTrue(user.favourites.isEmpty());
		Liftstation ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
		user.addFavourite(ls);
		assertFalse(user.favourites.isEmpty());
		user.removeFavourite(ls);
		assertTrue(user.favourites.isEmpty());
	}

}
