package controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import database.dao.ColorDao;
import database.dao.MaterialDao;
import database.dao.ProducerDao;
import database.dao.TextilCategoryDao;
import database.dao.TextilDao;
import database.dao.TextilSizeDao;
import database.datatypes.Role;
import database.extension.DatabaseEditor;
import database.extension.DatabaseTableController;
import database.extension.ParseInteger;
import database.tables.Color;
import database.tables.Material;
import database.tables.Producer;
import database.tables.Textil;
import database.tables.TextilCategory;
import database.tables.TextilSize;

@Controller
public class TextilController extends DatabaseTableController<Textil>{
	//implement ModelAttribute
	// add annotation for Model
	@Autowired
	public void autowired(ColorDao daoColor, MaterialDao daoMaterial, ProducerDao daoProducer, TextilSizeDao daoSize, TextilCategoryDao daoCategory){
		this.editorColor = new DatabaseEditor<Color>();
		this.editorMaterial = new DatabaseEditor<Material>();
		this.editorSize = new DatabaseEditor<TextilSize>();
		this.editorProducer = new DatabaseEditor<Producer>();
		this.editorCategory = new DatabaseEditor<TextilCategory>();	
		this.daoColor = daoColor;
		this.daoMaterial = daoMaterial;
		this.daoSize = daoSize;
		this.daoProducer = daoProducer;
		this.daoCategory = daoCategory;
	}
	
	private DatabaseEditor<Color> editorColor;
	private DatabaseEditor<Material> editorMaterial;
	private DatabaseEditor<TextilSize> editorSize;
	private DatabaseEditor<Producer> editorProducer;
	private DatabaseEditor<TextilCategory> editorCategory;
	private ColorDao daoColor;
	private MaterialDao daoMaterial;
	private TextilSizeDao daoSize;
	private ProducerDao daoProducer;
	private TextilCategoryDao daoCategory;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		editorProducer.setDao(daoProducer);
		editorProducer.setKeyType(new ParseInteger());
		binder.registerCustomEditor(Producer.class,editorProducer);
		editorColor.setDao(daoColor);
		editorColor.setKeyType(new ParseInteger());
		binder.registerCustomEditor(Color.class,editorColor);
		editorMaterial.setDao(daoMaterial);
		editorMaterial.setKeyType(new ParseInteger());
		binder.registerCustomEditor(Material.class,editorMaterial);
		editorSize.setDao(daoSize);
		editorSize.setKeyType(new ParseInteger());
		binder.registerCustomEditor(TextilSize.class,editorSize);
		editorCategory.setDao(daoCategory);
		editorCategory.setKeyType(new ParseInteger());
		binder.registerCustomEditor(TextilCategory.class,editorCategory);
	}
	
	@Autowired
	public TextilController(TextilDao dao) {
		super("Textil",dao, Textil.class , Role.WORKER);
	}

	//implement ModelAttribute
	// add annotation for Model
	
	/**
	 * DatabaseTableController contains the basic Functionallity
	 * To map it, you must cast these Methods to Controllers
	 * The Pathvalue contains /ROLE/METHODENAME(GET/POST)/FIRST SUPER PARAM
	 * */
	
	@RequestMapping(value = "/worker/addTextil", method = RequestMethod.GET)
	@Override
	public ModelAndView addGET() {
		return super.addGET();
	}
	
	
	@RequestMapping(value = "/worker/addTextil", method = RequestMethod.POST)
	@Override
	public ModelAndView addPOST(@ModelAttribute("form") Textil form, BindingResult result) {
		return super.addPOST(form, result);
	}
	
	@RequestMapping(value = "/worker/editTextil:{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView editGET(@PathVariable("id") int id) {
		return super.editGET(id);
	}

	
	@RequestMapping(value = "/worker/editTextil:{id}", method = RequestMethod.POST)
	@Override
	public ModelAndView editPOST(@PathVariable("id") int id, @ModelAttribute("form") Textil form, BindingResult result,Map<String, Object> model) {
		return super.editPOST(id, form, result, model);
	}

	
	@RequestMapping(value = "/worker/textils", method = RequestMethod.GET)
	@Override
	public ModelAndView viewsGET() {
		return super.viewsGET();
	}
	

	@RequestMapping(value = "/worker/ajaxTextil", method = RequestMethod.POST)//produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String ajax(@RequestParam(value = "content") String content) {
		String[] c = content.split(",");
		String type = c[0];
		String id = c[1];
		return super.ajax(type, id);
	}
}