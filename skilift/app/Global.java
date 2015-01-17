import java.io.File;

import models.Liftstation;
import play.*;

public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app) {
        
    	Liftstation.readXLSTable(new File("/play_project/Aufstiegshilfen.xls"));
    }
}
