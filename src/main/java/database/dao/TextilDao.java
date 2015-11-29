package database.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import database.extension.DatabaseException;
import database.tables.Color;
import database.tables.Material;
import database.tables.Producer;
import database.tables.Textil;
import database.tables.TextilCategory;
import database.tables.TextilSize;
@Repository
public class TextilDao extends DAO<Textil>{

	private ColorDao colordao;
	private TextilSizeDao sizedao;
	private MaterialDao materialdao;
	private ProducerDao producerdao;
	private TextilCategoryDao categorydao;
	
	@Autowired
	public TextilDao(TextilSizeDao sizedao,MaterialDao materialdao,ColorDao colordao, ProducerDao producerdao, TextilCategoryDao categorydao) {
		super(Textil.class);
		this.colordao = colordao;
		this.sizedao = sizedao;
		this.materialdao = materialdao;
		this.producerdao = producerdao;
		this.categorydao = categorydao;
	}

	@Override
	protected void updateSelect() {
		Map<String,String> colormap = new HashMap<String,String>();
		Map<String,String> sizemap = new HashMap<String,String>();
		Map<String,String> materialmap = new HashMap<String,String>();
		Map<String,String> producermap = new HashMap<String,String>();
		Map<String,String> categorymap = new HashMap<String,String>();
		try {
			List<Color> colorobjs = colordao.selectAll();
			for(Color o : colorobjs){
				o.addToMap(colormap);
			}
			List<TextilSize> sizeobjs = sizedao.selectAll();
			for(TextilSize o : sizeobjs){
				o.addToMap(sizemap);
			}
			List<Material> materialobjs = materialdao.selectAll();
			for(Material o : materialobjs){
				o.addToMap(materialmap);
			}
			List<Producer> producerobjs = producerdao.selectAll();
			for(Producer o : producerobjs){
				o.addToMap(producermap);
			}
			List<TextilCategory> categoryobjs = categorydao.selectAll();
			for(TextilCategory o : categoryobjs){
				o.addToMap(categorymap);
			}
		} catch (DatabaseException e) {
				e.printStackTrace();
		}
		super.addSelect("Color", colormap);
		super.addSelect("Size", sizemap);
		super.addSelect("Material", materialmap);
		super.addSelect("Producer", producermap);
		super.addSelect("Category", categorymap);
	}
	
	@Override
	public void delete(Serializable key) throws DatabaseException{
		super.deleteInteger(Integer.parseInt(key.toString()));
	}
	

}
