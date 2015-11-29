package database.dao;
import java.io.Serializable;

import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Invoice;
@Repository
public class InvoiceDao extends DAO<Invoice>{

	public InvoiceDao() {
		super(Invoice.class);
	}

	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
}
