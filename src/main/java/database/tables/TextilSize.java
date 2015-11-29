package database.tables;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import database.extension.DatabaseTable;

@Entity
public class TextilSize extends DatabaseTable<TextilSize>{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idsiz")
	@SequenceGenerator(initialValue = 1, name = "idsiz", sequenceName = "entitysiz")
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;
	private Integer width;
	private Integer height;
	private Integer length;
	private Integer chest;
	private Integer waist;
	
	public TextilSize (){
		super();
	}
	public TextilSize (String name, int width, int height, int length, int chest, int waist){
		this();
		this.name = name;
		this.width = width;
		this.height = height;
		this.length = length;
		this.waist = waist;
		this.chest = chest;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void initMapping() {
		// TODO Auto-generated method stub
	}
	@Override
	public void update(TextilSize instance) {
		typeMap.put("Width", "number");
		typeMap.put("Height", "number");
		typeMap.put("Length", "number");
		typeMap.put("Waist", "number");
		typeMap.put("Chest", "number");
	}
	
	@Override
	public String toString() {
		return ""+name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(this.checkString(name)){
			this.name = name;
		}
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		if(this.checkPositiveInteger(width)){
			this.width = width;
		}
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		if(this.checkPositiveInteger(height)){
			this.height = height;
		}
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		if(this.checkPositiveInteger(length)){
			this.length = length;
		}
	}
	public Integer getChest() {
		return chest;
	}
	public void setChest(Integer chest) {
		if(this.checkPositiveInteger(chest)){
			this.chest = chest;
		}
	}
	public Integer getWaist() {
		return waist;
	}
	public void setWaist(Integer waist) {
		if(this.checkPositiveInteger(waist)){
			this.waist = waist;
		}
	}
}
