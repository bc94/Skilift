import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

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

	static User user;
	

	@Test
	public void passChange() {
		running(fakeApplication(inMemoryDatabase("test")), () -> {
			user = new User("testMail", "abcd", "paypal");
			assertEquals(user.changePW("abcd", "efgh"), "Successfully changed password!");
			assertEquals(user.changePW("abcd", "efgh"), "Wrong password, try again.");
		});

	}
	
	@Test
	public void payChange() {
		running(fakeApplication(inMemoryDatabase("test")), () -> {
			user = new User("testMail", "abcd", "paypal");
			user.changePayment("test");
			assertEquals(user.paymentMethod, "test");
		});

	}
	
	@Test 
	public void addFav(){
		running(fakeApplication(inMemoryDatabase("test")), () -> {
			user = new User("testMail", "abcd", "paypal");
			Liftstation ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
			user.addFavourite(ls);
			assertEquals(ls, user.favourites.get(0));
		});

	}
	
	@Test
	public void remFav(){
		running(fakeApplication(inMemoryDatabase("test")), () -> {
			user = new User("testMail", "abcd", "paypal");
			assertTrue(user.favourites.isEmpty());
			Liftstation ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
			user.addFavourite(ls);
			assertFalse(user.favourites.isEmpty());
			user.removeFavourite(ls);
			assertTrue(user.favourites.isEmpty());
		});

	}
	
	@Test
	public void findTest() {
		running(fakeApplication(inMemoryDatabase("test")), () -> {
			user = new User("testMail", "abcd", "paypal");
			User test = user.find.ref(user.mail);
			assertEquals(user, test);
		});

	}
}
