package database.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.PrintCategory;

@Repository
public class PrintCategoryDao extends DAO<PrintCategory>{
	
	public PrintCategoryDao(){
		super(PrintCategory.class);
	}
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
