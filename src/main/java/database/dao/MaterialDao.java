package database.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Material;

@Repository
public class MaterialDao extends DAO<Material>{
	
	public MaterialDao() {
		super(Material.class);
	}

	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
