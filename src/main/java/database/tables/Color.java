package database.tables;

import java.util.Map;

import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import database.extension.DatabaseTable;

@Entity
public class Color extends DatabaseTable<Color>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idcol")
	@SequenceGenerator(initialValue = 1, name = "idcol", sequenceName = "entitycol")
	private int id;
	private Integer color;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;

	public Color(){
		super();
	}
	public Color(int color,String name){
		this();
		this.color = color;
		this.name = name;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void update(Color color) {
		if(this.checkObject(color)){
			this.setColor(color.getColor());
			this.setName(color.getName());
		}
	}
	
	@Override
	public void initMapping() {
			typeMap.put("Color", "color");
			typeMap.put("Id", "number");
	}
	
	@Override
	public String toString() {
		return ""+color;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getColor() {
		return color;
	}
	public void setColor(Integer color) {
		if(this.checkInteger(color)){
			this.color = color;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(this.checkString(name)){
			this.name = name;
		}
	}
	
}
