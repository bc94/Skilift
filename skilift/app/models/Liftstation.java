package models;

import com.avaje.ebean.annotation.ConcurrencyMode;
import com.avaje.ebean.annotation.EntityConcurrencyMode;
import controllers.Barrier;
import play.db.ebean.Model;

import java.io.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

import jxl.*;

import java.util.List;
import java.util.ArrayList;

import jxl.read.biff.BiffException;

@Entity
public class Liftstation extends Model {

	@Id
	public Integer ID;
	public String name;
	public Integer plz;
	public String type;
	public Integer capacity;
	private List<Barrier> barriers;
	
	public Liftstation(Integer id, String n, Integer p, String t, Integer c, List<Barrier> list) {
		ID = id;
		name = n;
		plz = p;
		type = t;
		capacity = c;
		barriers = list;
	}
	
	public static void create(Liftstation station){
		
		station.save();
	}

	public static Finder<Integer,Liftstation> find = new Finder<Integer,Liftstation>(
			Integer.class, Liftstation.class
	);

	public static List<Liftstation> findForName(String name) {
		return Liftstation.find.where().like("name",name+"%").findList();
	}
	
	public static void readXLSTable(File table){
		try{
				Sheet skilifts = Workbook.getWorkbook(table).getSheet(0);
				Cell cellName, cellID, cellPlz, cellType, cellCapacity;
				String name,type;
				Integer id, plz, capacity;
				for(int n = skilifts.getRows(), i = 3; i<n;i++){
					cellID = skilifts.getCell(1,i);
					cellName = skilifts.getCell(0,i);
					cellPlz = skilifts.getCell(2,i);
					cellType = skilifts.getCell(10,i);
					cellCapacity = skilifts.getCell(14,i);
					id = Integer.parseInt(cellID.getContents());
					name = cellName.getContents();
					if (cellPlz.getContents().isEmpty()) continue;
					else plz = Integer.parseInt(cellPlz.getContents());
					type = cellType.getContents();
					if (cellCapacity.getContents().isEmpty()) continue;
					else capacity = Integer.parseInt(cellCapacity.getContents());
					new Liftstation(id, name, plz, type, capacity, new ArrayList<Barrier>()).save();
				 }
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}catch (BiffException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
    
	public String getName(){
		
		return name;
	}
}
