package database.dao;
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Order;
@Repository
public class ChargeDao extends DAO<Order>{

	public ChargeDao() {
		super(Order.class);
	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}