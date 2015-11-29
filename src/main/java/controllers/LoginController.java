package controllers;



import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import database.dao.UserDao;
import database.datatypes.Role;
import database.datatypes.ValidationUser;
import database.extension.DatabaseException;
import database.tables.User;

	@Controller
	public class LoginController {
		
		@Autowired
		private UserDao dao;
		
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, 
            HttpServletRequest request) {

			ModelAndView model = new ModelAndView();
			if (error != null) {
				Exception exception = 
		                   (Exception) request.getSession().getAttribute( "SPRING_SECURITY_LAST_EXCEPTION");
				System.out.println(exception.getLocalizedMessage());
				model.addObject("error",exception.getMessage());
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("login");
			
			return model;
		}
		
	    @RequestMapping(value = "/register", method = RequestMethod.GET)
	    public String viewRegistration(Map<String, Object> model) {
	        ValidationUser userForm = new ValidationUser();    
	        model.put("userForm", userForm);
	        return "registration";
	    }
	    
		@RequestMapping(value = "/register",method = RequestMethod.POST)
	    public String processRegistration(@ModelAttribute("userForm") ValidationUser userForm, BindingResult result,
				Map<String, Object> model)  {
	 
			try {				
				User user = dao.mapValidationUser(userForm, true);
				user.setRole(Role.ADMIN);
				dao.save(user);
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
	        return "login";
	    }
		
	
	}
