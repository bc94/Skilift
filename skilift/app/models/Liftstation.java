package models;

import java.util.List;

public class Liftstation {

	private String name;
	private int ID;
	private String plz;
	private String type;
	private String capacity;
	private List<Barrier> barriers;
	
	public Liftstation(String n, String p, String t, String c, List<Barrier> list){
		
		name = n;
		plz = p;
		type = t;
		capacity = c;
		barriers = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public List<Barrier> getBarriers() {
		return barriers;
	}

	public void setBarriers(List<Barrier> barriers) {
		this.barriers = barriers;
	}

	public int getID() {
		return ID;
	}

}
