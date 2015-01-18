import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controllers.Barrier;
import models.Liftstation;


public class LiftstationTest {

	Liftstation ls = new Liftstation(1, "Teststation", 6020, "Testtype", 2, new ArrayList<Barrier>());
	
	@BeforeClass
	public void setUp() throws Exception {
		
		Liftstation.create(ls);
	}

	@Test
	public void findTest() {
		
		Liftstation test = ls.find.ref(ls.ID);
		assertEquals(ls, test);
	}

	@Test
	public void findForNameTest(){
		
		List<Liftstation> test = ls.findForName(ls.name);
		assertEquals(test, ls.findForName(ls.name));
	}
	
	@Test
	public void testXls(){
		
		Liftstation.readXLSTable(new File("/Aufstiegshilfen.xls"));
		List<Liftstation> test = ls.findForName("Brandtallift");
		assertEquals(test.get(0).name, "Brandtallift");
	}
}
