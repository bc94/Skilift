import java.io.File;

import models.Liftstation;
import models.User;
import play.*;

public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app) {
       if (Liftstation.find.findRowCount() == 0)
    		Liftstation.readXLSTable(new File(Play.application().path().getParent() + "/Aufstiegshilfen.xls"));
    }
}
