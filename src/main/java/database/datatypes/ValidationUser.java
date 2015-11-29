package database.datatypes;

import java.util.Map;

import database.extension.DatabaseTable;

public class ValidationUser extends DatabaseTable<ValidationUser>{
	private String firstname;
	private String lastname;
	private String email;
	private String pwd;
	private String pwd2;
	private String country;
	private String state;
	private String zip;
	private String city;
	private String street;
	private String housenumber;
	
	
	public boolean checkUser(){
		if(this.getPwd() == null || this.getPwd2() ==  null){
			return false;
		}
		return this.getPwd().equals(this.getPwd2());
	}
	
	@Override
	public void addToMap(Map<String, String> map) {
		map.put(""+email, firstname +" "+lastname+" "+email);
	}
	
	@Override
	public void initMapping() {
			typeMap.put("Email", "email");
			typeMap.put("Pwd", "password");
			typeMap.put("Pwd2", "password");
			typeMap.put("Zip", "number");
			nameMap.put("Pwd","Password");
			nameMap.put("Pwd2", "Confirm Password");
	}
	

	@Override
	public void update(ValidationUser instance) {
		// TODO Auto-generated method stub
	}
	
	
	public ValidationUser(){}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		if(checkString(firstname)){
			this.firstname = firstname;
		}
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		if(checkString(lastname)){	
			this.lastname = lastname;
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(checkString(email)){
			this.email = email;
		}
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		if(checkString(pwd)){	
			this.pwd = pwd;
		}
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		if(checkString(pwd2)){
			this.pwd2 = pwd2;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if(checkString(state)){	
			this.state = state;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if(checkString(city)){
			this.city = city;
		}
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		if(checkString(street)){
			this.street = street;
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
