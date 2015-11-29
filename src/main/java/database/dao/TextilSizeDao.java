package database.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.TextilSize;
@Repository
public class TextilSizeDao extends DAO<TextilSize>{

	public TextilSizeDao() {
		super(TextilSize.class);
	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}

}
