package database.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Color;

@Repository
public class ColorDao extends DAO<Color>{
	
	public ColorDao(){
		super(Color.class);
	}
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
