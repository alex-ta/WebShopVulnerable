package database.tables;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import database.extension.DatabaseTable;

@Entity
public class Textil extends DatabaseTable<Textil>{
	@Id
	@GeneratedValue
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;
	private Integer buyingprice;
	private Integer sellingprice;
	@Lob
	private String imgId;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Lob
	private String description;
	@IndexedEmbedded
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category",referencedColumnName="id")
	private TextilCategory category;
	@IndexedEmbedded
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="size",referencedColumnName="id")
	private TextilSize size;
	@IndexedEmbedded
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="material",referencedColumnName="id")
	private Material material;
	@IndexedEmbedded
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="color",referencedColumnName="id")
	private Color color;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="producer",referencedColumnName="id")
	private Producer producer;
	private Integer weight;
	
	public Textil(){
		super();
	}
	public Textil(String name, int buyingPrice, int sellingPrice, String imgId, String description, TextilCategory category, TextilSize size,
			Material material, Color color, Producer producer, int weight) {
		this();
		this.name = name;
		this.buyingprice = buyingPrice;
		this.sellingprice = sellingPrice;
		this.imgId = imgId;
		this.description = description;
		this.category = category;
		this.size = size;
		this.material = material;
		this.color = color;
		this.producer = producer;
		this.weight = weight;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void initMapping() {
		typeMap.put("Buyingprice", "number");
		typeMap.put("Sellingprice", "number");
		typeMap.put("Description", "textarea");
		typeMap.put("Category", "object");
		typeMap.put("Size", "object");
		typeMap.put("Material", "object");
		typeMap.put("Color", "object");
		typeMap.put("Producer", "object");
		typeMap.put("Weight", "number");
		typeMap.put("Category", "object");
	}
	@Override
	public void update(Textil instance) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public String toString() {
		return "Name: "+name+
			   " <br />Material: "+material+
			   " <br />Color: "+color+
			   " <br />Size: "+size;
		
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
	public Integer getBuyingprice() {
		return buyingprice;
	}
	public void setBuyingprice(int buyingPrice) {
		if(this.checkPositiveInteger(buyingPrice)){
			this.buyingprice = buyingPrice;
		}
	}
	public Integer getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(int sellingPrice) {
		if(this.checkPositiveInteger(sellingPrice)){
			this.sellingprice = sellingPrice;
		}
	}
	public String getImgid() {
		return imgId;
	}
	public void setImgid(String imgId) {
		if(this.checkString(imgId)){
			this.imgId = imgId;
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
	public TextilCategory getCategory() {
		return category;
	}
	public void setCategory(TextilCategory category) {
		if(this.checkObject(category)){
			this.category = category;
		}
	}
	public TextilSize getSize() {
		return size;
	}
	public void setSize(TextilSize size) {
		if(this.checkObject(size)){
			this.size = size;
		}
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		if(this.checkObject(material)){
			this.material = material;
		}
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		if(this.checkObject(color)){
			this.color = color;
		}
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		if(this.checkObject(producer)){
			this.producer = producer;
		}
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		if(this.checkInteger(weight)){
			this.weight = weight;
		}
	}
}
