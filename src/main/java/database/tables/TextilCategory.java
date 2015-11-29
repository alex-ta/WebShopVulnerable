package database.tables;

import java.util.Map;

import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import database.extension.DatabaseTable;

@Entity
public class TextilCategory extends DatabaseTable<TextilCategory>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idtcat")
	@SequenceGenerator(initialValue = 1, name = "idtcat", sequenceName = "entitytcat")
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;
	private String description;

	public TextilCategory(){
		super();
	}
	public TextilCategory(String name,String description){
		this();
		this.description = description;
		this.name = name;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void update(TextilCategory category) {
		if(this.checkObject(category)){
			this.setName(category.getName());
			this.setDescription(category.getDescription());
		}
	}
	
	@Override
	public void initMapping() {
			typeMap.put("Description", "textarea");
	}
	
	@Override
	public String toString() {
		return "Name: "+name+
			   " <br />Description: "+description;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(this.checkString(description)){
			this.description = description;
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
