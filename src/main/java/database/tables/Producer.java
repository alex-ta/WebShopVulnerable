package database.tables;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
public class Producer extends DatabaseTable<Producer>{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idprodr")
	@SequenceGenerator(initialValue = 1, name = "idprodr", sequenceName = "entityprodr")
	private int id;
	private String name;
	private String email;
	@Column(length=50)
	private String phone;
	@Lob
	private String website;
	@Embedded
	private Address address;
	private boolean known;
	
	public Producer(){
		super();
	}
	public Producer(String name, String email, String phone, String website, Address address) {
		this();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.address = address;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+id, name);
	}
	
	@Override
	public void initMapping() {
		typeMap.put("Email", "email");
		typeMap.put("Phone", "tel");
		typeMap.put("Website", "url");
		typeMap.put("Address", "address");
		typeMap.put("Known", "checkbox");
	}
	
	@Override
	public void update(Producer instance) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Name: "+name+
			   " <br />Email: "+email+
			   " <br />Phone: "+phone;		 
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(this.checkString(email)){
			this.email = email;
			}
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(this.checkString(phone)){
			this.phone = phone;
		}
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		if(this.checkString(website)){
			this.website = website;
		}
	}
	public boolean getKnown() {
		return known;
	}
	public void setKnown(boolean known) {
		this.known = known;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}	
	

}
