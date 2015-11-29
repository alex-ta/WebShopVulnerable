package database.tables;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import database.extension.DatabaseTable;

@Entity
@Indexed
/*
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "German")
  })
})
*/

public class Product extends DatabaseTable<Product>{
	@Id
	@GeneratedValue
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
	private String name;
	@IndexedEmbedded
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="textil",referencedColumnName="id")
	private Textil textil;
	@IndexedEmbedded
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="product_print",
	joinColumns = @JoinColumn(name="productid"),
	inverseJoinColumns = @JoinColumn(name="printid"))
	private List<Print> prints;
	private Integer buyingprice;
	private Integer sellingprice;
	
	public Product(){
		super();
		this.prints = new LinkedList<Print>();
	}

	public Product(String name, Textil textil, List<Print> prints, int buyingPrice, int sellingPrice) {
		this();
		this.name = name;
		this.textil = textil;
		this.prints = prints;
		this.buyingprice = buyingPrice;
		this.sellingprice = sellingPrice;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void initMapping() {
		typeMap.put("Textil", "object");
		typeMap.put("Print", "objects");
		typeMap.put("Buyingprice", "number");
		typeMap.put("Sellingprice", "number");
	}

	@Override
	public void update(Product instance) {
		// TODO Auto-generated method stub
		
	}
	
	public String toObject(){
		if(textil != null){
			StringBuilder images = new StringBuilder();
			StringBuilder printNames = new StringBuilder();
			for(int i=0; i< prints.size(); i++){
				if(i > 1){
					images.append(",");
					printNames.append("<br />");
				}
				Print p = prints.get(i);
				images.append("'"+p.getName()+"':'"+p.getImgid()+"'");
				printNames.append(p.getName());
			}
			
			
			
			StringBuilder content = new StringBuilder();
			content.append("Name:'"+name+"',");
			content.append("Size:'"+textil.getSize()+"',");
			content.append("Prints:'"+printNames+"',");
			content.append("Price:'"+getSellingprice()+"'");
		
			return "{img:{shirt:'"+this.getTextil().getImgid()+"', prints:{"+images+"}}, data:{"+content+"}}";
		}
		
		return null;
	}
	
	public void addPrint(Print print){
		prints.add(print);
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

	public Textil getTextil() {
		return textil;
	}

	public void setTextil(Textil textil) {
		if(this.checkObject(textil)){
			this.textil = textil;
		}
	}

	public List<Print> getPrint() {
		return prints;
	}

	public void setPrint(List<Print> prints) {
		if(this.checkObject(prints)){
			this.prints = prints;
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

	public Integer getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(Integer sellingPrice) {
		if(this.checkPositiveInteger(sellingPrice)){
			this.sellingprice = sellingPrice;
		}
	}
	
}
