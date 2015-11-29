package tests;

public abstract class DatabaseTable {
	
	public synchronized boolean checkString(String field){
		if(field != null && !(field.trim().equals(""))){
			return true;
		}
		return false;
	}
	
	public synchronized boolean checkObject(Object fd){
		if(fd != null){
			return true;
		}
		return false;
	}
}
