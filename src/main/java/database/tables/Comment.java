package database.tables;

import java.util.Date;
import java.util.Map;

import javax.persistence.*;

import database.extension.DatabaseTable;

@Entity
public class Comment extends DatabaseTable<Comment>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idcom")
	@SequenceGenerator(initialValue = 1, name = "idcom", sequenceName = "entitycom")
	private int id;	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product",referencedColumnName="id")
	private Product product;	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userid",referencedColumnName="email")
	private User user;
	private Date date;
	private Integer stars;
	@Lob
	private String comment;
	private boolean open;

	public Comment(){
		super();
	}

	public Comment(Product product, User user, Date date, int stars, String comment, boolean open) {
		this();
		this.product = product;
		this.user = user;
		this.date = date;
		this.stars = stars;
		this.comment = comment;
		this.open = open;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, user +" "+date);
	}

	@Override
	public void update(Comment instance) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initMapping() {
		typeMap.put("Open", "checkbox");
		typeMap.put("Stars", "stars");
		typeMap.put("Comment", "textarea");
		typeMap.put("User","object");
		typeMap.put("Product", "object");
	}

	@Override
	public String toString() {
		return "Stars: "+stars+
			   " <br />User: "+
			   user.getFirstname()+ 
			   user.getLastname()+
			   " <br />Comment: "+
			   " <br />"+comment;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if(this.checkObject(user)){
			this.product = product;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(this.checkObject(user)){
			this.user = user;
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		if(this.checkObject(user)){
			this.date = date;
		}
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		if(this.checkStars(stars)){
			this.stars = stars;
		}
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if(this.checkObject(user)){
			this.comment = comment;
		}
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
