import static org.junit.Assert.*;

import java.io.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

	static User user = new User("testMail", "abcd", "paypal");
	
	@BeforeClass
	public static void setUp() throws Exception{
		
		User.create(user);
	}
	
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
	
	@Test
	public void findTest() {
		
		User test = user.find.ref(user.mail);
		assertEquals(user, test);
	}
}
