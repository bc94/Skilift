package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Liftstation extends Model {

	@Id
	public int ID;
	public String name;
	public String plz;
	public String type;
	public Integer capacity;
	private List<Barrier> barriers;
	
	public Liftstation(String n, String p, String t, Integer c, List<Barrier> list){
		
		name = n;
		plz = p;
		type = t;
		capacity = c;
		barriers = list;
	}

	public static Finder<Integer,Liftstation> find = new Finder<Integer,Liftstation>(
			Integer.class, Liftstation.class
	);

	public static List<Liftstation> findForName(String name) {
		return Liftstation.find.where().like("name",name+"%").findList();
	}
}
