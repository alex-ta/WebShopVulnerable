package controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import classmapper.DevisionContext;
import classmapper.HTMLAttribute;
import classmapper.Mapper;
import database.dao.UserDao;
import database.datatypes.Role;
import database.datatypes.ValidationUser;
import database.extension.DatabaseException;
import database.extension.DatabaseTable;
import database.tables.User;

@Controller
public class EntityController {
	
	@Autowired
	private UserDao dao;
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public ModelAndView databaseUserMapped() {
	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth.getAuthorities().toString().contains("ROLE_"+Role.USER)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user = null;
		try {
			user = dao.select(userDetail.getUsername());
			ValidationUser userForm = dao.mapUser(user);
			Mapper mapper = dao.getValidationMapper();
			mapper.removeAll("pwd","pwd2","email");
			contentModel(model,userForm,mapper.mapRaw(userForm),"/user/edit");
		} catch (DatabaseException e) {
			e.printStackTrace();
		}	
	  }
	  model.setViewName("contentInput");
	  return model;
	}
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public ModelAndView databaseUserChanged(@ModelAttribute("form") ValidationUser userForm, BindingResult result,
			Map<String, Object> model)  {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getAuthorities().toString().contains("ROLE_"+Role.USER)) {
			try {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				User user = dao.select(userDetail.getUsername());
				User formUser = dao.mapValidationUser(userForm, false);
				user.updateWithoutCredentials(formUser);
				dao.update(user);
				Mapper mapper = dao.getValidationMapper();
				mapper.removeAll("pwd","pwd2","email");
				ValidationUser valid = dao.mapUser(user);
				contentModel(view,userForm,mapper.mapRaw(valid),"/user/edit");

			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		view.setViewName("contentInput");
		return view;
	}
	
	
	@RequestMapping(value = "/user/editPwd", method = RequestMethod.GET)
	public ModelAndView databaseCredentialsMapped() {
	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth.getAuthorities().toString().contains("ROLE_"+Role.USER)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user;
		try {
			user = dao.select(userDetail.getUsername());
			ValidationUser userForm = dao.mapUser(user);
			Mapper mapper = dao.getValidationMapper();
			mapper.removeAllExcept("pwd","pwd2");
			contentModel(model,userForm,mapper.mapRaw(userForm),"/user/editPwd");

		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	  }
	  model.setViewName("contentInput");
	  return model;
	}
	
	@RequestMapping(value = "/user/editPwd", method = RequestMethod.POST)
	public ModelAndView databaseCredentialsChanged(@ModelAttribute("form") ValidationUser userForm, BindingResult result,
			Map<String, Object> model)  {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getAuthorities().toString().contains("ROLE_"+Role.USER)) {			
			try {		
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				User user = dao.select(userDetail.getUsername());
				if(userForm.checkUser()){
					user.setPassword(userForm.getPwd());
				}
				dao.update(user);

				ValidationUser user2 = dao.mapUser(user);
				Mapper mapper = dao.getValidationMapper();
				mapper.removeAllExcept("pwd","pwd2");
				
				contentModel(view,user2,mapper.mapRaw(user2),"/user/editPwd");
		
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		view.setViewName("contentInput");
		return view;
	}
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public ModelAndView adminShowUsers()  {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth.getAuthorities().toString().contains("ROLE_"+Role.ADMIN)) {
			try {
				Mapper mapper = dao.getUserMapper();
				DevisionContext ctx = new DevisionContext();
				HTMLAttribute attr = new HTMLAttribute();
				attr.addWrapperAttribute("class","table table-bordered table-striped");
				ctx.htmlContext("table", attr);
				mapper.setContext(ctx);
				List<User> list;
				list = dao.selectAll();
				view.addObject("content", mapper.map(list));
				view.setViewName("contentShow");
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return view;
	}
	
	public void contentModel(ModelAndView model, DatabaseTable<ValidationUser> form, Map<String,String> map, String url){
		model.addObject("contentMap",map);
		model.addObject("contentType",form.typeMap);
		model.addObject("contentName",form.nameMap);
		model.addObject("form", new ValidationUser());
		model.addObject("targetURL",url);
		
	}
	
	/*
	@RequestMapping(value = "/admin/credentials", method = RequestMethod.POST)
	public ModelAndView adminCredentialsChanged(@ModelAttribute("userForm") ValidationUser userForm, BindingResult result,
			Map<String, Object> model)  {
		
	}
	*/
	
}
