package database.dao;
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Producer;
@Repository
public class ProducerDao extends DAO<Producer>{

	public ProducerDao() {
		super(Producer.class);
	}

	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
