package database.tables;

import java.util.Date;
import java.util.Map;

import javax.persistence.*;

import database.datatypes.Role;
import database.extension.DatabaseTable;


@Entity(name="user_account")
public class User extends DatabaseTable<User>{
	
	@Id
	private String email;
	@Column(length=100)
	private String password; 
	private String firstname;
	private String lastname;
	@Embedded
	private Address address;
	private Role role;
	private Date created;
	private boolean enabled;
	private boolean expired;
	private boolean locked;
	private boolean emailValid;
	
	
	public User(String email, String password, String firstname, String lastname, Address address, Role role,
			Date created, boolean enabled, boolean expired, boolean locked, boolean emailValid) {
		this();
		this.email = email; 
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.role = role;
		this.created = created;
		this.enabled = enabled;
		this.expired = expired;
		this.locked = locked;
		this.emailValid = emailValid;
	}
	
	/*
	// defaults
	public User(){
		super();
		this.created = new Date();
		this.enabled = false;
		this.expired = false;
		this.locked = false;
		this.emailValid = false;
	}
	*/
	public User(){
		super();
	}
	
	
	public User(User user) {
		this();
		this.address = new Address();
		update(user);
	}
	
	@Override
	public void update(final User user){
		updateCredentials(user);
		updateWithoutCredentials(user);
	}
	@Override
	public void addToMap(Map<String, String> map) {
		System.out.println("not supported");
	}

	@Override
	public void initMapping() {
			typeMap.put("Email", "email");
			typeMap.put("Password", "password");
			typeMap.put("Created", "date");
			typeMap.put("Enabled", "checkbox");
			typeMap.put("Expired", "checkbox");
			typeMap.put("Locked", "checkbox");
			typeMap.put("EmailValid", "checkbox");
	}
	
	public void updateWithoutCredentials(final User user){
		if(user == null){
			return;
		}
		this.getAddress().update(user.getAddress());
		this.setFirstname(user.getFirstname());
		this.setLastname(user.getLastname());
		this.setCreated(user.getCreated());
	}
	
	public void updateCredentials(final User user){
		if(user == null){
			return;
		}
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setEmailValid(user.getEmailValid());
		this.setEnabled(user.getEnabled());
		this.setLocked(user.getLocked());
		this.setExpired(user.getExpired());
		this.setRole(user.getRole());
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(checkString(email)){
			this.email = email;
		}
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if(checkObject(address)){
			this.address = address;
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if(checkObject(role)){
			this.role = role;
		}
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		if(checkObject(created)){
			this.created = created;
		}
	}

	public void setPassword(String password) {
		if(checkString(password)){
			this.password = password;
		}
	}

	public String getPassword() {
		return password;
	}
	
	public boolean getExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean getLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean getEmailValid() {
		return emailValid;
	}

	public void setEmailValid(boolean emailValid) {
		this.emailValid = emailValid;
	}

	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
