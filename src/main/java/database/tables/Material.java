package database.tables;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import database.extension.DatabaseTable;

@Entity
public class Material extends DatabaseTable<Material>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idmat")
	@SequenceGenerator(initialValue = 1, name = "idmat", sequenceName = "entitymat")
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;
	@Lob
	private String description;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Lob
	private String ingredients;
	
	public Material(){
		super();
	}
	public Material(String name, String description, String ingredients){
		this();
		this.name = name;
		this.ingredients = ingredients;
		this.description = description;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void update(Material material) {
		if(this.checkObject(material)){
			this.setName(material.getName());
			this.setDescription(material.getDescription());
			this.setIngredients(material.getIngredients());
		}
	}
	
	@Override
	public void initMapping() {
		typeMap.put("Description", "textarea");
		typeMap.put("Ingredients", "textarea");
	}
	
	@Override
	public String toString() {
		return "Name: "+name+
			   " <br />Description: "+description+
			   " <br />Ingredients: "+ingredients;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(this.checkString(description)){
			this.description = description;
		}
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		if(this.checkString(ingredients)){
			this.ingredients = ingredients;
		}
	}

}
