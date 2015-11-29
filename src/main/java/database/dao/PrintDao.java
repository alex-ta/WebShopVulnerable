package database.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Color;
import database.tables.Print;
import database.tables.PrintCategory;
@Repository
public class PrintDao extends DAO<Print>{
	
	private ColorDao colorDao;
	private PrintCategoryDao categorydao;
	@Autowired
	public PrintDao(ColorDao colorDao,  PrintCategoryDao categorydao) {
		super(Print.class);
		this.colorDao = colorDao;
		this.categorydao = categorydao;
	}

	@Override
	protected void updateSelect() {
		Map<String,String> map = new HashMap<String,String>();
		Map<String,String> categorymap = new HashMap<String,String>();
		try {
			List<Color> objs = colorDao.selectAll();
			for(Color o : objs){
				o.addToMap(map);
			}
			List<PrintCategory> categoryobjs = categorydao.selectAll();
			for(PrintCategory o : categoryobjs){
				o.addToMap(categorymap);
			}
		} catch (DatabaseException e) {
				e.printStackTrace();
		}
		super.addSelect("Color", map);
		super.addSelect("Category", categorymap);

	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
	
	
}
