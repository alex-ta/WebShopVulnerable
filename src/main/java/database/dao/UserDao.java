package database.dao;
 
import java.io.Serializable;

import org.springframework.stereotype.Repository;
import classmapper.Mapper;
import database.datatypes.ValidationUser;
import database.extension.DatabaseException;
import database.tables.*;

@Repository
public class UserDao extends DAO<User>{
 
	private Mapper mapperValidation;
	
	public UserDao(){
		super(User.class,"email");
		mapperValidation= new Mapper(ValidationUser.class);
	}
	
	public Mapper getValidationMapper(){
		return new Mapper(mapperValidation);
	}
	
	public Mapper getUserMapper(){
		return this.getMapper();
	}
	
    @Override
    public void save(User obj) throws DatabaseException{
    	if(obj != null){
    		obj.setEnabled(true);
    		obj.setEmailValid(true);
			super.save(obj);
		}    
	}

	
	public User mapValidationUser(final ValidationUser user, final boolean checkPwd){
		User u = new User();
		// Mapp & check
		if(!checkPwd || user.checkUser()){
			u.setPassword(user.getPwd());
			u.setEmail(user.getEmail());
			u.setFirstname(user.getFirstname());
			u.setLastname(user.getLastname());
			Address address = new Address();
			address.setCity(user.getCity());
			address.setCountry(user.getCountry());
			address.setState(user.getState());
			address.setStreet(user.getStreet());
			address.setHousenumber(user.getHousenumber());
			address.setZip(user.getZip());
			u.setAddress(address);
		} 
		return u;
	}
    
	public ValidationUser mapUser(final User user){
		// Mapp & check
		ValidationUser u = new ValidationUser();
		if(user != null){
			u.setPwd("*****");
			u.setPwd2("*****");
			u.setEmail(user.getEmail());
			u.setFirstname(user.getFirstname());
			u.setLastname(user.getLastname());
			u.setCity(user.getAddress().getCity());
			u.setCountry(user.getAddress().getCountry());
			u.setState(user.getAddress().getState());
			u.setStreet(user.getAddress().getStreet());
			u.setHousenumber(user.getAddress().getHousenumber());
			u.setZip(user.getAddress().getZip());
		} 
		return u;
	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteString(key.toString());
	}
}