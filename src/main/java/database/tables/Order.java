package database.tables;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import database.extension.DatabaseTable;

@Entity
@Table(name="product_order")
public class Order extends DatabaseTable<Order>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idord")
	@SequenceGenerator(initialValue = 1, name = "idord", sequenceName = "entityord")
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userid",referencedColumnName="email")
	private User user;
	@ElementCollection
    @CollectionTable(name="product_basket",
    joinColumns=@JoinColumn(name="orderid"))
    @Column(name="counts")
    @MapKeyJoinColumn(name="prodcutid", referencedColumnName="id")
	private Map<Product,Integer> products;
	private Date created;
	private Integer sellingprice;
	@Embedded
	private Address address;
	
	public Order(){
		super();
		this.products = new HashMap<Product,Integer>();
		this.created = new Date();
	}
	public Order(User user, int sellingPrice,Address address) {
		this();
		this.user = user;
		this.sellingprice = sellingPrice;
		this.address = address;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		System.out.println("cannt be put in a map");
	}
	
	@Override
	public void update(Order instance) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initMapping() {
		typeMap.put("User", "object");
		typeMap.put("Sellingprice", "number");
		typeMap.put("Address", "object");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(this.checkObject(user)){
			this.user = user;
		}
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		if(this.checkObject(created)){
			this.created = created;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		if(this.checkObject(address)){
			this.address = address;
		}
	}
	
	public Map<Product,Integer> getProducts() {
		return products;
	}
	
	public void setProducts(Map<Product,Integer> products) {
		if(this.checkObject(products)){
			this.products = products;
		}
	}
	
}


