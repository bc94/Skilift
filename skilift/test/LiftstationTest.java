import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controllers.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controllers.Barrier;
import models.Liftstation;
import play.api.test.FakeApplication;
import play.test.Helpers;


public class LiftstationTest {

	static Liftstation ls;

	@Test
	public void findTest() {
			running(fakeApplication(inMemoryDatabase("test")), () -> {
			ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
			Liftstation.create(ls);
			Liftstation test = ls.find.ref(ls.ID);
			assertEquals(ls, test);
			});
		}


	@Test
	public void findForNameTest(){
		running(fakeApplication(inMemoryDatabase("test")), () -> {
			ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
			Liftstation.create(ls);
			List<Liftstation> test = ls.findForName(ls.name);
			assertEquals(test, ls.findForName(ls.name));
		});

	}

	@Test
	public void testXls(){
			running(fakeApplication(inMemoryDatabase("test")), () -> {
				ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
				Liftstation.create(ls);
				Liftstation.readXLSTable(new File("/Aufstiegshilfen.xls"));
				List<Liftstation> test = ls.findForName("Brandtallift");
				assertEquals(test.get(0).name, "Brandtallift");
			});

		}

	}

