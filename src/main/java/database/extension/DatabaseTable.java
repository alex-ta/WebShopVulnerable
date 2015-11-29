package database.extension;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class DatabaseTable<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 01102015L;

	public DatabaseTable(){
		typeMap = new HashMap<String,String>();
		nameMap = new HashMap<String,String>();
		initMapping();
	}
	
	public boolean checkString(String field){
		if(field != null && !(field.trim().equals(""))){
			return true;
		}
		return false;
	}
	
	public boolean checkObject(Object fd){
		if(fd != null){
			return true;
		}
		return false;
	}
	
	public boolean checkInteger(Integer i){
		if(i !=  null){
			return true;
		}
		return false;
	}
	
	public boolean checkPositiveInteger(Integer i){
		if(i !=  null && i > 0){
			return true;
		}
		return false;
	}
	
	public boolean checkStars(Integer i){
		if(i !=  null && i>0 && i<6){
			return true;
		}
		return false;
	}
	
	public Map<String,String> typeMap;
	public Map<String,String> nameMap;
	
	public String wrapType(String key){
		if(nameMap.containsKey(key)){
			return typeMap.get(key);
		}
		return "text";
	}
	public String wrapName(String key){
		if(nameMap.containsKey(key)){
			return nameMap.get(key);
		}
		return key;
	}
	
	public abstract void initMapping();
	public abstract void update(T instance);
	public abstract void addToMap(Map<String,String> map);
}
