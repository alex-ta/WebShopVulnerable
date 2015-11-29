package controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import database.dao.PrintCategoryDao;
import database.datatypes.Role;
import database.extension.DatabaseTableController;
import database.tables.PrintCategory;

@Controller
public class PrintCategoryController extends DatabaseTableController<PrintCategory>{
	

	@Autowired
	public PrintCategoryController(PrintCategoryDao dao) {
		super("CategoryPrint",dao, PrintCategory.class , Role.WORKER);
	}
	
	/**
	 * DatabaseTableController contains the basic Functionallity
	 * To map it, you must cast these Methods to Controllers
	 * The Pathvalue contains /ROLE/METHODENAME(GET/POST)/FIRST SUPER PARAM
	 * */
	
	@RequestMapping(value = "/worker/addCategoryPrint", method = RequestMethod.GET)
	@Override
	public ModelAndView addGET() {
		return super.addGET();
	}
	
	
	@RequestMapping(value = "/worker/addCategoryPrint", method = RequestMethod.POST)
	@Override
	public ModelAndView addPOST(@ModelAttribute("form") PrintCategory form, BindingResult result) {
		return super.addPOST(form, result);
	}
	
	@RequestMapping(value = "/worker/editCategoryPrint:{id}", method = RequestMethod.GET)
	@Override
	public ModelAndView editGET(@PathVariable("id") int id) {
		return super.editGET(id);
	}

	
	@RequestMapping(value = "/worker/editCategoryPrint:{id}", method = RequestMethod.POST)
	@Override
	public ModelAndView editPOST(@PathVariable("id") int id, @ModelAttribute("form") PrintCategory form, BindingResult result,Map<String, Object> model) {
		return super.editPOST(id, form, result, model);
	}

	
	@RequestMapping(value = "/worker/categorysPrint", method = RequestMethod.GET)
	@Override
	public ModelAndView viewsGET() {
		return super.viewsGET();
	}


	@RequestMapping(value = "/worker/ajaxCategoryPrint", method = RequestMethod.POST)//produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String ajax(@RequestParam(value = "content") String content) {
		String[] c = content.split(",");
		String type = c[0];
		String id = c[1];
		return super.ajax(type, id);
	}
}
