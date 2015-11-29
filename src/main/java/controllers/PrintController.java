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
import database.dao.PrintCategoryDao;
import database.dao.PrintDao;
import database.datatypes.Role;
import database.extension.DatabaseEditor;
import database.extension.DatabaseTableController;
import database.extension.ParseInteger;
import database.tables.Color;
import database.tables.Print;
import database.tables.PrintCategory;

@Controller
public class PrintController extends DatabaseTableController<Print>{
	//implement ModelAttribute
	// add annotation for Model
	@Autowired
	public void autowired(ColorDao daoColor, PrintCategoryDao daoCategory){
		this.editorColor = new DatabaseEditor<Color>();
		this.editorCategory = new DatabaseEditor<PrintCategory>();
		this.daoColor = daoColor;
		this.daoCategory = daoCategory;
	}
	
	private DatabaseEditor<Color> editorColor;
	private DatabaseEditor<PrintCategory> editorCategory;
	private ColorDao daoColor;
	private PrintCategoryDao daoCategory;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		editorColor.setDao(daoColor);
		editorColor.setKeyType(new ParseInteger());
		binder.registerCustomEditor(Color.class,editorColor);
		editorCategory.setDao(daoCategory);
		editorCategory.setKeyType(new ParseInteger());
		binder.registerCustomEditor(PrintCategory.class,editorCategory);
	}
	
	@Autowired
	public PrintController(PrintDao dao) {
		super("Print",dao, Print.class , Role.WORKER);
	}
	
	/**
	 * DatabaseTableController contains the basic Functionallity
	 * To map it, you must cast these Methods to Controllers
	 * The Pathvalue contains /ROLE/METHODENAME(GET/POST)/FIRST SUPER PARAM
	 * */
	
	@RequestMapping(value = "/worker/addPrint", method = RequestMethod.GET)
	@Override
	public ModelAndView addGET() {
		return super.addGET();
	}
	
	
	@RequestMapping(value = "/worker/addPrint", method = RequestMethod.POST)
	@Override
	public ModelAndView addPOST(@ModelAttribute("form") Print form, BindingResult result) {
		return super.addPOST(form, result);
	}
	
	@RequestMapping(value = "/worker/editPrint:{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView editGET(@PathVariable("id") int id) {
		return super.editGET(id);
	}

	
	@RequestMapping(value = "/worker/editPrint:{id}", method = RequestMethod.POST)
	@Override
	public ModelAndView editPOST(@PathVariable("id") int id, @ModelAttribute("form") Print form, BindingResult result,Map<String, Object> model) {
		return super.editPOST(id, form, result, model);
	}

	
	@RequestMapping(value = "/worker/prints", method = RequestMethod.GET)
	@Override
	public ModelAndView viewsGET() {
		return super.viewsGET();
	}
	

	@RequestMapping(value = "/worker/ajaxPrint", method = RequestMethod.POST)//produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String ajax(@RequestParam(value = "content") String content) {
		String[] c = content.split(",");
		String type = c[0];
		String id = c[1];
		return super.ajax(type, id);
	}
}
