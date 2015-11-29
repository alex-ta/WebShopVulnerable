package controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import database.dao.ColorDao;
import database.dao.ProductDao;
import database.datatypes.Role;
import database.extension.DatabaseException;
import database.extension.DatabaseTableController;
import database.tables.Color;
import database.tables.Product;

@Controller
public class PageController {
	
	
	/**
	 * DatabaseTableController contains the basic Functionallity
	 * To map it, you must cast these Methods to Controllers
	 * The Pathvalue contains /ROLE/METHODENAME(GET/POST)/FIRST SUPER PARAM
	 * */
	@Autowired
	ProductDao dao;
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView addGET() {
		ModelAndView model = new ModelAndView();
		model.setViewName("productOverview");
		List<Product> products = null;
		try {
			products = dao.selectAll();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer content = new StringBuffer();
		content.append("[");
		int i = 0;
		for(Product p : products){
			if(i++ > 0){
				content.append(",");
			}
			content.append(p.toObject());
		}
		content.append("]");
		
		model.addObject("content", content);
		model.addObject("contentHeader","Overview");
		return model;
	}
	
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ModelAndView addPOST(@ModelAttribute("form") Color form, BindingResult result) {
		ModelAndView model = new ModelAndView();
			
		return model;
	}
	
	@RequestMapping(value = "/page/ajax{id}", method = RequestMethod.POST)
	@ResponseBody
	public String ajax(@ModelAttribute("form") Color form, BindingResult result) {
			
		return "";
	}
	
	
}
