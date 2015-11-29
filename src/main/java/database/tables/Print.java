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
public class Print extends DatabaseTable<Print>{
	@Id
	@GeneratedValue
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;
	private Integer buyingprice;
	@Lob
	private String imgid;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	@Lob
	private String description;
	@IndexedEmbedded
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category",referencedColumnName="id")
	private PrintCategory category;
	private Integer width;
	private Integer height;
	@IndexedEmbedded
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="color",referencedColumnName="id")
	private Color color;
	private Integer sellingprice;

	public Print(){
		super();
	}
	public Print(String name, Integer buyingprice, String imgid, String description, Integer width, Integer height, Color color,
			Integer sellingprice, PrintCategory category) {
		this();
		this.name = name;
		this.buyingprice = buyingprice;
		this.imgid = imgid;
		this.description = description;
		this.width = width;
		this.height = height;
		this.color = color;
		this.sellingprice = sellingprice;
		this.category = category;
	}

	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void update(Print instance) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initMapping() {
		typeMap.put("Buyingprice", "number");
		typeMap.put("Sellingingprice", "number");
		typeMap.put("Description", "textarea");
		typeMap.put("Width", "number");
		typeMap.put("Height", "number");
		typeMap.put("Color", "object");
		typeMap.put("Category", "object");
	}
	
	@Override
	public String toString() {
		return "Name: "+name+
			   "<br />Color: "+color;
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
	public void setBuyingprice(Integer buyingPrice) {
		if(this.checkPositiveInteger(buyingPrice)){
			this.buyingprice = buyingPrice;
		}
	}
	public String getImgid() {
		return imgid;
	}
	public void setImgid(String imgId) {
		if(this.checkString(imgId)){	
			this.imgid = imgId;
		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(this.checkObject(description)){
			this.description = description;
		}
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		if(this.checkInteger(width)){
			this.width = width;
		}
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		if(this.checkInteger(height)){
			this.height = height;
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
	public Integer getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(Integer sellingPrice) {
		if(this.checkPositiveInteger(sellingPrice)){
			this.sellingprice = sellingPrice;
		}
	}
	public PrintCategory getCategory() {
		return category;
	}
	public void setCategory(PrintCategory category) {
		if(this.checkObject(category)){
			this.category = category;
		}
	}

}
