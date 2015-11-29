package database.dao;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Print;
import database.tables.Product;
import database.tables.Textil;
@Repository
public class ProductDao extends DAO<Product>{

	private TextilDao textildao;
	private PrintDao printdao;
	
	@Autowired
	public ProductDao(TextilDao textildao,PrintDao printdao) {
		super(Product.class);
		this.printdao = printdao;
		this.textildao = textildao;
	}

	@Override
	protected void updateSelect() {
		Map<String,String> textilmap = new HashMap<String,String>();
		Map<String,String> printmap = new HashMap<String,String>();
		try {
			List<Textil> textilobjs = textildao.selectAll();
			for(Textil o : textilobjs){
				o.addToMap(textilmap);
			}
			List<Print> printobjs = printdao.selectAll();
			for(Print o : printobjs){
				o.addToMap(printmap);
			}
		} catch (DatabaseException e) {
				e.printStackTrace();
		}
		super.addSelect("Textil", textilmap);
		super.addSelect("Print", printmap);
	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
	
}
