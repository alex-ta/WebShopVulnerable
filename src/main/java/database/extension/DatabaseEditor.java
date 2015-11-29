package database.extension;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;

import database.dao.DAO;

public class DatabaseEditor<result> extends PropertyEditorSupport{
	
	private ParseInterface<? extends Serializable> keyType;
	private DAO<result> dao;
	
	public DatabaseEditor(){
		super();
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try{
			if(keyType != null){
				this.setValue(dao.select(keyType.parse(text)));
			}else{
				this.setValue(dao.select(text));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public ParseInterface<? extends Serializable> getKeyType() {
		return keyType;
	}

	public void setKeyType(ParseInterface<? extends Serializable> keyType) {
		this.keyType = keyType;
	}

	public DAO<result> getDao() {
		return dao;
	}

	public void setDao(DAO<result> dao) {
		this.dao = dao;
	}
	
}
