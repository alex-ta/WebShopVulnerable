package controllers;

import java.util.List;
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

import database.dao.PrintDao;
import database.dao.ProductDao;
import database.dao.TextilDao;
import database.datatypes.Role;
import database.extension.DatabaseEditor;
import database.extension.DatabaseListEditor;
import database.extension.DatabaseTableController;
import database.extension.ParseInteger;
import database.tables.Print;
import database.tables.Product;
import database.tables.Textil;

@Controller
public class ProductController extends DatabaseTableController<Product>{
	@Autowired
	public void autowired(TextilDao daoTextil,PrintDao daoPrint){
		this.editorTextil = new DatabaseEditor<Textil>();
		this.daoTextil = daoTextil;
		this.editorPrint = new DatabaseListEditor<Print>();
		this.daoPrint = daoPrint;

	}
	
	private DatabaseListEditor<Print> editorPrint;
	private PrintDao daoPrint;
	private DatabaseEditor<Textil> editorTextil;
	private TextilDao daoTextil;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		editorTextil.setDao(daoTextil);
		editorTextil.setKeyType(new ParseInteger());
		binder.registerCustomEditor(Textil.class,editorTextil);
		editorPrint.setDao(daoPrint);
		editorPrint.setKeyType(new ParseInteger());
		binder.registerCustomEditor(List.class,editorPrint);
	}
	
	@Autowired
	public ProductController(ProductDao dao) {
		super("Product",dao, Product.class , Role.WORKER);
	}
	
	/**
	 * DatabaseTableController contains the basic Functionallity
	 * To map it, you must cast these Methods to Controllers
	 * The Pathvalue contains /ROLE/METHODENAME(GET/POST)/FIRST SUPER PARAM
	 * */
	
	@RequestMapping(value = "/worker/addProduct", method = RequestMethod.GET)
	@Override
	public ModelAndView addGET() {
		return super.addGET();
	}
	
	
	@RequestMapping(value = "/worker/addProduct", method = RequestMethod.POST)
	@Override
	public ModelAndView addPOST(@ModelAttribute("form") Product form, BindingResult result) {
		return super.addPOST(form, result);
	}
	
	@RequestMapping(value = "/worker/editProduct:{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView editGET(@PathVariable("id") int id) {
		return super.editGET(id);
	}

	
	@RequestMapping(value = "/worker/editProduct:{id}", method = RequestMethod.POST)
	@Override
	public ModelAndView editPOST(@PathVariable("id") int id, @ModelAttribute("form") Product form, BindingResult result,Map<String, Object> model) {
		return super.editPOST(id, form, result, model);
	}

	
	@RequestMapping(value = "/worker/products", method = RequestMethod.GET)
	@Override
	public ModelAndView viewsGET() {
		return super.viewsGET();
	}
	

	@RequestMapping(value = "/worker/ajaxProduct", method = RequestMethod.POST)//produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String ajax(@RequestParam(value = "content") String content) {
		String[] c = content.split(",");
		String type = c[0];
		String id = c[1];
		return super.ajax(type, id);
	}

}
