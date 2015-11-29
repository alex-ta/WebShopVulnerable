package database.extension;

import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import database.dao.DAO;

public class DatabaseListEditor<result> extends PropertyEditorSupport{
	
	private ParseInterface<? extends Serializable> keyType;
	private DAO<result> dao;
	
	public DatabaseListEditor(){
		super();
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		List<result> list = new LinkedList<result>();
		String[] rows = text.split(",");
		for (int i = 0; i < rows.length; i++) {
			String s = rows[i];
			try{
				if(keyType != null){
					list.add(dao.select(keyType.parse(s)));
				}else{
					list.add(dao.select(s));
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("----");
		System.out.println(list);
		System.out.println("----");
		this.setValue(list);
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
