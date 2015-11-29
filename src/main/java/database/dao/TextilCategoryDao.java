package database.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.TextilCategory;

@Repository
public class TextilCategoryDao extends DAO<TextilCategory>{
	
	public TextilCategoryDao(){
		super(TextilCategory.class);
	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
