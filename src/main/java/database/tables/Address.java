package database.tables;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import database.extension.DatabaseTable;

@Embeddable
public class Address extends DatabaseTable<Address>{
	private String housenumber;
	private String street;
	private String city;
	@Column(length=20)
	private String zip;
	private String state;
	private String country;
	
	@Override
	public void initMapping() {
			typeMap.put("Zip", "number");
	}
	
	public Address(){
		super();
	}
	public Address(String street, String city, String zip, String state, String county, String housenumber) {
		this();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = county;
		this.housenumber = housenumber;
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		System.out.println("cannt be put in a map");
	}

	@Override
	public void update(Address address){
		if(address == null){
			return;
		}
		this.setState(address.getState());
		this.setCountry(address.getCountry());
		this.setCity(address.getCity());
		this.setZip(address.getZip());
		this.setStreet(address.getStreet());
		this.setHousenumber(address.getHousenumber());	
	}
	
	@Override
	public String toString() {
		return "Country: "+country+
			   " <br />State: "+state+
			   " <br />City: "+city+
			   " <br />ZIP: "+zip+
		       " <br />Street: "+street+" "+housenumber;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		if(checkString(street)){
			this.street = street;
		}
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if(checkString(city)){
			this.city = city;
		}
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		if(checkString(zip)){
			this.zip = zip;
		}
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if(checkString(state)){
			this.state = state;
		}
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		if(checkString(country)){
			this.country = country;
		}
	}
	public String getHousenumber() {
		return housenumber;
	}
	public void setHousenumber(String housenumber) {
		if(checkString(housenumber)){
			this.housenumber = housenumber;
		}
	}
}
